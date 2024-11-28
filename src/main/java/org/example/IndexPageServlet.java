package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;


import java.io.IOException;

@WebServlet("/index")
public class IndexPageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }
}

