package org.example.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Month;
import org.example.model.MonthRepository;

import java.io.IOException;

@WebServlet("/month/save")
public class MonthSaveServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ObjectMapper mapper = new ObjectMapper();

        try {
            Month newMonth = mapper.readValue(request.getReader(), Month.class);

            boolean exists = MonthRepository.getInstance().getMonthList().stream()
                    .anyMatch(month -> month.getId().equals(newMonth.getId()));

            if (exists) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("Month with this ID already exists.");
                return;
            }

            MonthRepository.getInstance().addMonth(newMonth);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            mapper.writeValue(response.getWriter(), newMonth);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid JSON format or missing fields.");
        }
    }

}
