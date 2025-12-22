package auth;

import database.Database;
import model.User;
import ui.Dashboard;

import java.util.Scanner;

public class LoginOrRegister {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Database.init();

        while (true) {
            System.out.println("\n=== WELCOME ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("> ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                User loggedInUser = login();
                if (loggedInUser != null) {
                    Dashboard dashboard = new Dashboard(loggedInUser);
                    dashboard.show();
                }
            } else if (choice == 2) {
                register();
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static User login() {
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (AuthService.login(fullName, password)) {
            User user = AuthService.getUser(fullName);
            System.out.println("Welcome, " + user.fullName + "!");
            return user;
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }

    private static void register() {
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Gender (M/F/Other): ");
        String gender = sc.nextLine();

        User user = new User();
        user.fullName = fullName;
        user.email = email;
        user.age = age;
        user.gender = gender;

        if (AuthService.register(user, password)) {
            System.out.println("Account created! You can now log in.");
        } else {
            System.out.println("Full Name already exists.");
        }
    }
}
