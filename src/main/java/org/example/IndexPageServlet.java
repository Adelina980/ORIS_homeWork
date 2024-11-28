package org.example;

import org.example.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;


@WebServlet("")
public class IndexPageServlet extends HttpServlet {


    public IndexPageServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");


        try {
            Connection connection = DBConnection.getInstance().getConnection(); // Получаем соединение из синглтона
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(
                        new User(resultSet.getLong("id"),
                                resultSet.getString("name")));
            }

            request.setAttribute("users", users);
            request.setAttribute("title_page", "Пользователи БД");


            request.getRequestDispatcher("index.ftl").forward(request, response);
//            DBConnection.releaseConnection(connection);
            preparedStatement.close();
            resultSet.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
