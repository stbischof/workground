package org.eclipse.daanse.xmla.server.authentication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardFilterPattern;

import java.io.IOException;
import java.util.Base64;

import static org.osgi.service.servlet.context.ServletContextHelper.AUTHENTICATION_TYPE;
import static org.osgi.service.servlet.context.ServletContextHelper.REMOTE_USER;

@Component(scope = ServiceScope.SINGLETON)
@HttpWhiteboardFilterPattern("/xmla3")
public class AuthFilter implements Filter {

    public static final String ADMIN = "admin";

    @Override
    public void init(FilterConfig filterConfig) {
        //empty
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getHeader("Authorization") == null) {
            httpResponse.addHeader("WWW-Authenticate", "Basic");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if (authenticated(httpRequest)) {
            chain.doFilter(request, response);//sends request to next resource
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    protected boolean authenticated(HttpServletRequest request) {
        request.setAttribute(AUTHENTICATION_TYPE, HttpServletRequest.BASIC_AUTH);
        boolean success = false;
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
        	String usernameAndPassword = new String(Base64.getDecoder().decode(authHeader.substring(6).getBytes()));

        	int userNameIndex = usernameAndPassword.indexOf(":");
        	String username = usernameAndPassword.substring(0, userNameIndex);
        	String password = usernameAndPassword.substring(userNameIndex + 1);


        	success = (username.equals(ADMIN) && password
        			.equals(ADMIN));
        	if (success) {
        		request.setAttribute(REMOTE_USER, ADMIN);
        		request.setAttribute("ROLE", ADMIN);
        	}
        }
        return success;
    }

    @Override
    public void destroy() {
        //empty
    }
}
