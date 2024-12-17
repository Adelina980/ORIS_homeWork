package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Profession;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionRepository {

//    final static Logger logger = LogManager.getLogger(ProfessionRepository.class);

    private DbWork db = DbWork.getInstance();

    public List<Profession> findAll(int limit, int offset) {

        List<Profession> professions = new ArrayList<>();
        String sql = "SELECT * FROM dict_profession LIMIT ? OFFSET ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Profession profession = new Profession();
                profession.setId(resultSet.getLong("id"));
                profession.setName(resultSet.getString("name"));
                professions.add(profession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professions;
    }

    public List<Profession> findByName(String name, int limit, int offset) {

        List<Profession> professions = new ArrayList<>();
        String sql = "SELECT * FROM dict_profession WHERE LOWER(name) = LOWER(?) LIMIT ? OFFSET ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.println(preparedStatement);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Profession profession = new Profession();
                profession.setId(resultSet.getLong("id"));
                profession.setName(resultSet.getString("name"));
                professions.add(profession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professions;
    }

    public Integer countResults(String name) {
        String sql = "SELECT COUNT(*) FROM dict_profession";
        if (name != null && !name.isEmpty()) {
            sql += " WHERE name = ?";
        }

        int count = 0;
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (name != null && !name.isEmpty()) {
                preparedStatement.setString(1, name);
            }


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
