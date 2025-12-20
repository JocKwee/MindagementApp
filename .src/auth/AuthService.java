package auth;

import java.util.Scanner; 
import model.User;

public class AuthService {
    private Scanner scanner = new Scanner(System.in);

public User loginOrSignUp() {
    System.out.println("Welcome to Mindagement");
    System.out.println("1. Login");
    System.out.println("2. Sign Up");
    System.out.print("Choose an option: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice == 1) {
        return login();
    } else {
        return signUp();
    }
}

private User login() {      // fix this no way
    System.out.print("Enter your full name: ");
    String fullName = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    System.out.println("Login successful!");
    return new User();
}

private User signUp() {
    System.out.print("Enter your full name: ");
    String fullName = scanner.nextLine();
    System.out.print("Choose a password: ");
    String password = scanner.nextLine();

    System.out.println("Sign up successful!");
    return new User();
}
}