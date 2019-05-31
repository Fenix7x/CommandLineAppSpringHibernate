package com.minsk.bh.service;

import com.minsk.bh.dao.UserDAO;
import com.minsk.bh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    private final Scanner scanner = new Scanner(System.in);

    public void login() {
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        User userCheck = userDAO.login(user);

        try {
            if (userCheck.getLogin().equals(login) && userCheck.getPassword().equals(password)) {
                if (userCheck.getName().equals("Adm")) {
                    System.out.println("Hello, " + userCheck.getName());
                    printUsers();
                } else {
                    System.out.println("Hello, " + userCheck.getName());
                }
            }
        } catch (NullPointerException e) {
            System.err.println("User does not exist.");
        }
    }

    public void registration() {
        System.out.println("Enter User name:");
        String name = scanner.nextLine();

        System.out.println("Enter login:");
        String login = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        userDAO.registration(new User(name, login, password, false));
    }

    public void addTestUsers() {
        User user1 = new User("First", "f1", "123", false);
        User user2 = new User("Second", "s1", "456", false);
        User user3 = new User("Third", "t1", "789", false);
        User user4 = new User("Adm", "aaa", "111", true);
        userDAO.registration(user1);
        userDAO.registration(user2);
        userDAO.registration(user3);
        userDAO.registration(user4);
    }

    public void printUsers() {
        List<User> users = userDAO.getAllUsers();
        users.forEach(System.out::println);
    }
}
