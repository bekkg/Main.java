package org.peaksoft;

import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.service.CarServiceImpl;
import org.peaksoft.service.UserServiceImpl;
import org.peaksoft.util.Util;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // TODO: 27.09.2023   реализуйте алгоритм здесь

//        System.out.println(Util.connection());
        
          CarServiceImpl carService = new CarServiceImpl();

        Car car = new Car("mersedes", LocalDate.of(2012,12,7),"white");
//        carService.createTable();
//        carService.dropTable();
//        carService.save(car);
//        System.out.println(carService.getAll());
//        carService.removeById(1);
//        carService.cleanTable();




        UserServiceImpl userService = new UserServiceImpl();

        User user = new User("Bekbolsun","Bekmurza uulu", (byte)25,1L);
//        userService.createTable();
//        userService.dropTable();
//        userService.save(user);
//        System.out.println(userService.getAll());
//        carService.removeById(1);
//        carService.cleanTable();

    }
}
