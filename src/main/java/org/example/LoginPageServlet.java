package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;


@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(LoginPageServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("/login");

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//        request.getRequestDispatcher("login.ftl").forward(request, response);
    }

}
