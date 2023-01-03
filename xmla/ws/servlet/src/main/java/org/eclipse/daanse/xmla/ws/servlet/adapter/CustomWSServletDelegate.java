package org.eclipse.daanse.xmla.ws.servlet.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.ws.transport.http.HttpAdapter;
import com.sun.xml.ws.transport.http.servlet.JAXWSRIServletProbeProvider;
import com.sun.xml.ws.util.exception.JAXWSExceptionBase;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.http.HTTPBinding;

public class CustomWSServletDelegate {

    private final CustomServletAdapter customServletAdapter;
    private final JAXWSRIServletProbeProvider probe = new JAXWSRIServletProbeProvider();

    public CustomWSServletDelegate(CustomServletAdapter customServletAdapter) {

        this.customServletAdapter = customServletAdapter;
        HttpAdapter.setPublishStatus(Boolean.FALSE);

    }

    public void destroy() {

    }

    public void doHead(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException {

        try {
            customServletAdapter.handle(context, request, response);
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

            final String path = request.getContextPath() + customServletAdapter.getValidPath();
            probe.startedEvent(path);

            customServletAdapter.invokeAsync(context, request, response, new HttpAdapter.CompletionCallback() {
                @Override
                public void onCompletion() {
                    probe.endedEvent(path);
                }
            });

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
        try {

            Binding binding = customServletAdapter.getEndpoint()
                    .getBinding();
            if (binding instanceof HTTPBinding) {
                customServletAdapter.handle(context, request, response);
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

        // At present, there is no difference for between PUT and DELETE processing
        doPut(request, response, context);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomWSServletDelegate.class);

}
