package org.example.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Month;
import org.example.model.MonthRepository;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/month/list")
public class MonthListServlet extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            mapper.writeValue(response.getWriter(), MonthRepository.getInstance().getMonthList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
