package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


        public class Main {

            private static final UserService userService = new UserServiceImpl();

            public static void main(String[] args) {


                userService.createUsersTable();
                userService.saveUser("Jensen","Ackles", (byte) 45);
                userService.saveUser("Misha","Collins", (byte) 49);
                userService.saveUser("Jared","Padalecki", (byte) 41);
                userService.saveUser("Mark","Sheppard", (byte) 59);
                userService.removeUserById(67);
                userService.getAllUsers();
                userService.cleanUsersTable();
                userService.dropUsersTable();


            }
        }


