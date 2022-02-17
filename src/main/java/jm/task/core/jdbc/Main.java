package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("Ivan", "Ivanov" ,(byte) 15);
        userServiceImpl.saveUser("Oleg", "Olegov" ,(byte) 23);
        userServiceImpl.saveUser("Elena", "Elenova" ,(byte) 19);
        userServiceImpl.saveUser("Igor", "Igorev" ,(byte) 42);

        System.out.println(userServiceImpl.getAllUsers());

        userServiceImpl.cleanUsersTable();

        userServiceImpl.dropUsersTable();

    }
}
