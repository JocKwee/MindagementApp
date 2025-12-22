package auth;

import model.User;
import ui.DashboardGUI;

import javax.swing.*;
import java.awt.*;

public class LoginOrRegisterGUI extends JFrame {

    public LoginOrRegisterGUI() {
        setTitle("Login / Register");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1, 5, 5));

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> showLoginDialog());
        registerBtn.addActionListener(e -> showRegisterDialog());
    }

    private void showLoginDialog() {
        JTextField nameField = new JTextField();
        JPasswordField passField = new JPasswordField();

        Object[] message = {
                "Full Name:", nameField,
                "Password:", passField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String fullName = nameField.getText();
            String password = new String(passField.getPassword());

            if (AuthService.login(fullName, password)) {
                User user = AuthService.getUser(fullName);
                JOptionPane.showMessageDialog(this, "Welcome, " + fullName + "!");
                new DashboardGUI(user);
                dispose(); // close login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        }
    }

    private void showRegisterDialog() {
        JTextField nameField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField ageField = new JTextField();
        String[] genders = {"M", "F", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);

        Object[] message = {
                "Full Name:", nameField,
                "Password:", passField,
                "Email:", emailField,
                "Age:", ageField,
                "Gender:", genderBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            User user = new User();
            user.fullName = nameField.getText();
            user.email = emailField.getText();
            user.age = Integer.parseInt(ageField.getText());
            user.gender = (String) genderBox.getSelectedItem();
            String password = new String(passField.getPassword());

            if (AuthService.register(user, password)) {
                JOptionPane.showMessageDialog(this, "Account created! You can now log in.");
            } else {
                JOptionPane.showMessageDialog(this, "Full Name already exists.");
            }
        }
    }
}