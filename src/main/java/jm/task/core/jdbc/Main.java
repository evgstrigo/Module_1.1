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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Connection c = MySQLConnUtils.getMySQLConnection();
//        Statement statement = c.createStatement();
//        String sql = "select * from Users";
//        ResultSet rs = statement.executeQuery(sql);
//        rs.next();
//        System.out.print(rs.getInt(1) + "*** ");
//        System.out.print(rs.getString(2) + "*** ");
//        System.out.print(rs.getString(3) + "*** ");
//        System.out.println(rs.getInt(4));
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.dropUsersTable();
//        userServiceImpl.createUsersTable();
//        userServiceImpl.saveUser("Ivan", "Valetov", (byte) 19);
//        userServiceImpl.saveUser("Oleg", "Ragalevich", (byte) 32);
//        userServiceImpl.saveUser("Eugenio", "Fioletov", (byte) 24);
//        userServiceImpl.saveUser("Lampa", "Pampura", (byte) 32);
//        userServiceImpl.saveUser("Olga", "Kozyreva", (byte) 37);
//
//        for(User u : userServiceImpl.getAllUsers()) {
//            System.out.println(u.getId() + "****" + u.getName() + "****" + u.getLastName() + "****" + u.getAge());
//        }

        userServiceImpl.cleanUsersTable();



    }
}
