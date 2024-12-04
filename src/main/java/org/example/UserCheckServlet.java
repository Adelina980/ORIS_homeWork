package org.example;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");



        String sql = "SELECT * FROM users WHERE name = ?";
//        String sql = "INSERT INTO users (name, password_hash) VALUES (?,?)";
        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            String salt = BCrypt.gensalt(10);
            String hash = BCrypt.hashpw(userPassword, salt);
            preparedStatement.setString(1, userName);
//            preparedStatement.setString(2, hash);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                if (bCrypt.matches(userPassword,
                        resultSet.getString("password_hash"))){
                    UUID uuid = UUID.randomUUID();

                    Map<UUID, Long> authentificationData =
                            (Map<UUID, Long>) request.getServletContext().getAttribute("USER_SESSIONS");
                    authentificationData.put(uuid,resultSet.getLong("id"));
                    // JSESSIONID
                    // Клиент получит действующую куку
                    HttpSession session = request.getSession();
                    // Сохраним в сессии пользователя его имя
                    session.setAttribute("user", userName);
                    session.setAttribute("SECRET_KEY", uuid.toString());
                    response.sendRedirect(getServletContext().getContextPath() + "/index");
                }else{
                    response.sendRedirect(getServletContext().getContextPath() + "/login");
                }
            }else {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
            }
//            response.sendRedirect(getServletContext().getContextPath() + "/index");
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
