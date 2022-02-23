package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {


        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov" ,(byte) 15);
        userService.saveUser("Oleg", "Olegov" ,(byte) 23);
        userService.saveUser("Elena", "Elenova" ,(byte) 19);
        userService.saveUser("Igor", "Igorev" ,(byte) 42);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();

        Util.closeConnection();
    }
}
