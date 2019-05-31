package com.minsk.bh;

import com.minsk.bh.persistence.SpringConfig;
import com.minsk.bh.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Menu {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private static final String EXIT = "q";
    private final Scanner scanner = new Scanner(System.in);
    private String input = "";

    void start() {
        context.getBean(UserServiceImpl.class).addTestUsers();
        try {
            while (!input.equals(EXIT)) {
                printMainLegend();
                input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("\nEnter login:");
                        context.getBean(UserServiceImpl.class).login();
                        break;
                    case "2":
                        System.out.println("\nRegistration:");
                        context.getBean(UserServiceImpl.class).registration();
                        break;
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void printMainLegend() {
        System.out.println("\nSelect action:");
        System.out.println("1. Sign in");
        System.out.println("2. Registration");
        System.out.println("q. Exit");
    }
}