package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;



@WebServlet("/index")
public class IndexPageServlet extends HttpServlet {
    final static Logger logger = (Logger) LogManager.getLogger(IndexPageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = (String) request.getSession(false)
                .getAttribute("user");

        logger.debug(username);
        logger.info(username + "-info");
        logger.error(username + "-error");


        request.setAttribute("username", username);

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
//        request.getRequestDispatcher("index.ftl").forward(request,response);
    }
}

