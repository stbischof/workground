package org.eclipse.daanse.xmla.ws.jakarta.basic.internal.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.ws.resources.WsservletMessages;
import com.sun.xml.ws.transport.http.HttpAdapter;
import com.sun.xml.ws.transport.http.servlet.JAXWSRIServletProbeProvider;
import com.sun.xml.ws.transport.http.servlet.ServletAdapter;
import com.sun.xml.ws.util.exception.JAXWSExceptionBase;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.http.HTTPBinding;

public class CustomWSServletDelegate {

    private final Map<String, CustomServletAdapter> fixedUrlPatternEndpoints = new HashMap<>();
    private final List<CustomServletAdapter> pathUrlPatternEndpoints = new ArrayList<>();
    private final JAXWSRIServletProbeProvider probe = new JAXWSRIServletProbeProvider();

    public CustomWSServletDelegate() {

        LOGGER.atInfo()
                .log(WsservletMessages.SERVLET_INFO_INITIALIZE());

        HttpAdapter.setPublishStatus(Boolean.FALSE);

    }

    public void destroy() {
        LOGGER.atInfo()
                .log(WsservletMessages.SERVLET_INFO_DESTROY());
    }

    public void doHead(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {

        try {
            ServletAdapter target = getTarget(request);
            if (target != null) {
                LOGGER.atDebug()
                        .log(WsservletMessages.SERVLET_TRACE_GOT_REQUEST_FOR_ENDPOINT(target.getName()));

                target.handle(context, request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (JAXWSExceptionBase e) {

            LOGGER.atError()
                    .setCause(e)
                    .log();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            LOGGER.atError()
                    .setCause(e)
                    .log();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {

        try {
            ServletAdapter target = getTarget(request);
            if (target != null) {
                LOGGER.atDebug()
                        .log(WsservletMessages.SERVLET_TRACE_GOT_REQUEST_FOR_ENDPOINT(target.getName()));

                final String path = request.getContextPath() + target.getValidPath();
                probe.startedEvent(path);

                target.invokeAsync(context, request, response, new HttpAdapter.CompletionCallback() {
                    @Override
                    public void onCompletion() {
                        probe.endedEvent(path);
                    }
                });
            } else {
                writeNotFoundErrorPage(response, "Invalid Request");
            }
        } catch (JAXWSExceptionBase e) {
            LOGGER.atError()
                    .setCause(e)
                    .log();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            LOGGER.atError()
                    .setCause(e)
                    .log();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {
        doGet(request, response, context);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {
        // TODO: unify this into doGet.
        try {
            ServletAdapter target = getTarget(request);
            if (target != null) {
                LOGGER.atDebug()
                        .log(WsservletMessages.SERVLET_TRACE_GOT_REQUEST_FOR_ENDPOINT(target.getName()));

            } else {
                writeNotFoundErrorPage(response, "Invalid request");
                return;
            }
            Binding binding = target.getEndpoint()
                    .getBinding();
            if (binding instanceof HTTPBinding) {
                target.handle(context, request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } catch (JAXWSExceptionBase e) {
            LOGGER.atError()
                    .setCause(e)
                    .log();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            LOGGER.atError()
                    .setCause(e)
                    .log();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {

        // At preseent, there is no difference for between PUT and DELETE processing
        doPut(request, response, context);
    }

    private void writeNotFoundErrorPage(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>");
        out.println(WsservletMessages.SERVLET_HTML_TITLE());
        out.println("</title></head>");
        out.println("<body>");
        out.println(WsservletMessages.SERVLET_HTML_NOT_FOUND(message));
        out.println("</body>");
        out.println("</html>");
    }

    public void unregisterEndpointUrlPattern(CustomServletAdapter a) {
        pathUrlPatternEndpoints.remove(a);

        for (Entry<String, CustomServletAdapter> e : fixedUrlPatternEndpoints.entrySet()) {
            if (e.getValue() == a) {
                fixedUrlPatternEndpoints.remove(e.getKey());
            }
        }

    }

    public void registerEndpointUrlPattern(CustomServletAdapter a) {
        String urlPattern = a.urlPattern;
        if (urlPattern.contains("*.")) {
            // cannot deal with implicit mapping right now
            LOGGER.atWarn()
                    .log(

                            WsservletMessages.SERVLET_WARNING_IGNORING_IMPLICIT_URL_PATTERN(a.getName()));
        } else if (urlPattern.endsWith("/*")) {
            pathUrlPatternEndpoints.add(a);
        } else {
            if (fixedUrlPatternEndpoints.containsKey(urlPattern)) {
                LOGGER.atWarn()
                        .log(WsservletMessages.SERVLET_WARNING_DUPLICATE_ENDPOINT_URL_PATTERN(a.getName()));
            } else {
                fixedUrlPatternEndpoints.put(urlPattern, a);
            }
        }
    }

    /**
     * Determines which {@link ServletAdapter} serves the given request.
     * 
     * @param request request
     * @return the adapter
     */
    protected ServletAdapter getTarget(HttpServletRequest request) {

        String path = request.getRequestURI()
                .substring(request.getContextPath()
                        .length());
        ServletAdapter result = fixedUrlPatternEndpoints.get(path);
        if (result == null) {
            for (ServletAdapter candidate : pathUrlPatternEndpoints) {
                String noSlashStar = candidate.getValidPath();
                if (path.equals(noSlashStar) || path.startsWith(noSlashStar + "/")
                        || path.startsWith(noSlashStar + "?")) {
                    result = candidate;
                    break;
                }
            }
        }

        return result;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomWSServletDelegate.class);

}
