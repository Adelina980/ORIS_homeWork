package org.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {

    private static String SECRET_KEY = "password";
    private static String SECRET_KEY_NAME = "secret_key";

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");

        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UUID uuid = UUID.randomUUID();

                Map<UUID, Long> authentificationData =
                        (Map<UUID, Long>) request.getServletContext().getAttribute("AUTH_DATA");
                authentificationData.put(uuid,resultSet.getLong("id"));
//                request.getServletContext().setAttribute("AUTH_DATA", authentificationData);
                response.addCookie(new Cookie("SECRET_KEY", uuid.toString()));
                response.sendRedirect(getServletContext().getContextPath() + "/index");
            } else {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
//        response.sendRedirect(getServletContext().getContextPath() +
//                "/usercheck?userName=" + name + "&userPassword=" + password);
//
//    }
}
