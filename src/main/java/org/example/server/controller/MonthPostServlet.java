package org.example.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Month;
import org.example.model.MonthRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/month/*")
public class MonthPostServlet extends HttpServlet {




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo != null && pathInfo.matches("^/\\d+$")) {
                String id = pathInfo.substring(1);
                ObjectMapper mapper = new ObjectMapper();

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                mapper.writeValue(response.getWriter(), MonthRepository.getInstance().getMonthList().get(Integer.parseInt(id) - 1));

            }
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Месяц не найден");
        }
    }

}
