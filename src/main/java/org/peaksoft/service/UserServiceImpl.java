package org.peaksoft.service;


import org.peaksoft.model.User;
import org.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserServiceImpl implements Service<User> {
public void createTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE  TABLE  IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(50) NOT NULL ," +
                    "lastName VARCHAR(50) NOT NULL ," +
                    "age INTEGER NOT NULL," +
                    "carId INTEGER REFERENCES cars(id))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP  TABLE  IF EXISTS users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void save(User user) {
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(" INSERT INTO users (name,lastName,age,carId)" +
                             "VALUES (?,?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.setLong(4, user.getCarId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeById(long id) {
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM  users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM users")){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                user.setCarId(resultSet.getLong("carid"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanTable() {
        try(Connection connection=Util.connection();
            PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE  users") ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(long id) {
        User user = new User();
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement("  SELECT  * FROM users WHERE  id = ? ")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                user.setCarId(resultSet.getLong("CarId"));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
