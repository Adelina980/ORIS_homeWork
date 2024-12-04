package org.example.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@WebFilter("/*")
public class AutentificationFilter extends HttpFilter {
    final static Logger logger = (Logger) LogManager.getLogger(AutentificationFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        logger.info("Аутентифицируем " + request.getRemoteAddr() + ":" + request.getRemotePort()
                + " " + Utils.upper(httpServletRequest.getServletPath()));

        if (httpServletRequest.getServletPath().startsWith("/static/") ||
                httpServletRequest.getServletPath().startsWith("/usercheck")) {

            logger.debug("Запрос статического ресурса " + request.getRemoteAddr() + ":" + request.getRemotePort()
                    + " " + httpServletRequest.getServletPath());


            filterChain.doFilter(request, response);

        } else {
            if (httpServletRequest.getCookies() != null) {
                Map<UUID, Long> authentificationData =
                        (Map<UUID, Long>) request.getServletContext().getAttribute("USER_SESSIONS");
                HttpSession session = httpServletRequest.getSession(false);

                if (session != null) {
                    String sessionValue = (String) session.getAttribute("SECRET_KEY");
                    if (sessionValue != null){
                        try {
                            UUID uuid = UUID.fromString(sessionValue);
                            if (authentificationData.containsKey(uuid)) {
                                filterChain.doFilter(request, response);
                            } else {
                                request.getRequestDispatcher("/login").forward(request, response);
                            }
                        } catch (IllegalArgumentException e) {
                            request.getRequestDispatcher("/login").forward(request, response);
                        }
                    }else {
                        request.getRequestDispatcher("/login").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/login").forward(request, response);
                }
            }else {
                request.getRequestDispatcher("/login").forward(request, response);
            }

        }
    }
}
