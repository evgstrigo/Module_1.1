package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }


    public void createUsersTable() {
        String sql = "create table if not exists Users (\n" +
                "ID integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME varchar(30),\n" +
                "LASTNAME varchar(30),\n" +
                "AGE integer\n" +
                ")";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
    }


    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Table USERS is DROPPED");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (name, lastname, age) values (?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя");
            System.out.println("Внезапно возникло исключение " + e);
        }

    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID = " + id + " удалён");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsersList = new ArrayList<>();
        String sql = "select * from USERS";
        User tempUser;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tempUser = new User(); // эта строка обязательно должна быть внутри цикла
                                       // иначе все объекты в листе будут одинаковыми
                tempUser.setId(resultSet.getLong("ID"));
                tempUser.setName(resultSet.getString("NAME"));
                tempUser.setLastName(resultSet.getString("LASTNAME"));
                tempUser.setAge(resultSet.getByte("AGE"));
                allUsersList.add(tempUser);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
        return allUsersList;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }

    }
}
