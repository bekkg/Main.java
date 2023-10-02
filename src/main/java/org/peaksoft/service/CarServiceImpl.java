package org.peaksoft.service;


import org.peaksoft.model.Car;
import org.peaksoft.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements Service<Car> {
    public void createTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE  TABLE IF NOT EXISTS   cars (" +
                    "id SERIAL PRIMARY KEY ," +
                    "model VARCHAR  ," +
                    "yearofRelease DATE  ," +
                    "color VARCHAR  )  ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP  TABLE IF EXISTS   cars");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(Car car) {

        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO  cars (model,yearofRelease,color)" +
                             "VALUES (?,?,?)") ) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setDate(2, Date.valueOf(car.getYearOfRelease()));
            preparedStatement.setString(3, car.getColor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeById(long id) {

        try ( Connection connection = Util.connection();
            PreparedStatement preparedStatement = connection.prepareStatement  ("DELETE  FROM  cars WHERE id = ?")  ) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public List<Car> getAll() {
        List <Car> list = new ArrayList<>();
        try (Connection connection = Util.connection();
                Statement statement=connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(
                    "SELECT  *FROM cars");
            while ( resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setModel(resultSet.getString("model"));
                car.setYearOfRelease(resultSet.getDate("yearOfRelease").toLocalDate());
                car.setColor(resultSet.getString("color"));
                list.add(car);
            }
        } catch (SQLException e ){
         e.printStackTrace();
        }
        return list;
    }

    public void cleanTable() {
        try(Connection connection=Util.connection();
          PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE  cars") ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car getById(long id) {
        Car car = new Car();
        try (  Connection connection = Util.connection();
               PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM cars WHERE id == ?") ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            car.setModel(resultSet.getString("model"));
            car.setYearOfRelease(resultSet.getDate("yearOfRelease").toLocalDate());
            car.setColor(resultSet.getString("color"));
            preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return car;
    }
}
