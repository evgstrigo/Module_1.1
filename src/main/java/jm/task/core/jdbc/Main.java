package jm.task.core.jdbc;

import com.mysql.cj.MysqlConnection;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.ConnectionUtils;
import jm.task.core.jdbc.util.MySQLConnUtils;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.dropUsersTable();
        userServiceImpl.createUsersTable();


        userServiceImpl.saveUser("Ivan", "Valetov", (byte) 19);
        userServiceImpl.saveUser("Oleg", "Ragalevich", (byte) 32);
        userServiceImpl.saveUser("Eugenio", "Fioletov", (byte) 24);
        userServiceImpl.saveUser("Lampa", "Pampura", (byte) 32);
        userServiceImpl.saveUser("Olga", "Kozyreva", (byte) 37);

        for (User u : userServiceImpl.getAllUsers()) {
            System.out.println(u);
        }

        userServiceImpl.cleanUsersTable();
        userServiceImpl.removeUserById(300);


    }
}
