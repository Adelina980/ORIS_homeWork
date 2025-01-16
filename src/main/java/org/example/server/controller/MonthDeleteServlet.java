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

@WebServlet("/month/delete/*")
public class MonthDeleteServlet extends HttpServlet {
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Month ID is required in the URL.");
            return;
        }

        try {
            Long id = Long.parseLong(pathInfo.substring(1));

            boolean deleted = MonthRepository.getInstance().deleteMonthById(id);

            if (deleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Month with ID " + id + " was successfully deleted.");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Month with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid ID format. Must be a numeric value.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

