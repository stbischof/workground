/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
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
    public static final String USER1 = "user1";
    public static final String ROLE1 = "role1";
    public static final String USER2 = "user2";
    public static final String ROLE2 = "role2";
    public static final String USER3 = "user3";
    public static final String ROLE3 = "role3";

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
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
        if (authenticated(requestWrapper)) {
            chain.doFilter(requestWrapper, response);//sends request to next resource
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    protected boolean authenticated(HeaderMapRequestWrapper request) {
        request.setAttribute(AUTHENTICATION_TYPE, HttpServletRequest.BASIC_AUTH);
        boolean success = false;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
        	String usernameAndPassword = new String(Base64.getDecoder().decode(authHeader.substring(6).getBytes()));

        	int userNameIndex = usernameAndPassword.indexOf(":");
        	String username = usernameAndPassword.substring(0, userNameIndex);
        	String password = usernameAndPassword.substring(userNameIndex + 1);


        	success = (username.equals(ADMIN) && password.equals(ADMIN))
                || (username.equals(USER1) && password.equals(USER1))
                || (username.equals(USER2) && password.equals(USER2))
                || (username.equals(USER3) && password.equals(USER3));

        	if (success) {
        	    switch (username) {
                    case ADMIN:
                        request.setAttribute(REMOTE_USER, ADMIN);
                        request.setAttribute("ROLE", ADMIN);
                        //request.addHeader("ROLE", ADMIN);
                        request.addHeader("USER", ADMIN);
                        break;
                    case USER1:
                        request.setAttribute(REMOTE_USER, USER1);
                        request.addHeader("ROLE", ROLE1);
                        request.addHeader("USER", USER1);
                        break;
                    case USER2:
                        request.setAttribute(REMOTE_USER, USER2);
                        request.addHeader("ROLE", ROLE2);
                        request.addHeader("USER", USER2);
                        break;
                    case USER3:
                        request.setAttribute(REMOTE_USER, USER3);
                        request.addHeader("ROLE", ROLE3);
                        request.addHeader("USER", USER3);
                        break;
                }
        	}
        }
        return success;
    }

    @Override
    public void destroy() {
        //empty
    }

}
