package auth;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import model.User;
import ui.DashboardGUI;

public class LoginOrRegisterGUI extends JFrame {

    public LoginOrRegisterGUI() {
        setTitle("Mindagement");
        setSize(1080, 1920);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Main background
        JPanel background = new JPanel();
        background.setBackground(Color.decode("#1f3282"));
        background.setBounds(0, 0, 1080, 1920);
        background.setLayout(null);
        add(background);

        // Heading
        JLabel heading = new JLabel("Mindagement", SwingConstants.CENTER);
        heading.setFont(new Font("HK Modular", Font.BOLD, 60));
        heading.setForeground(Color.WHITE);
        heading.setBounds(0, 50, 1080, 100);
        background.add(heading);

        // Login box with rounded corners
        JPanel loginBox = new RoundedPanel(30, new Color(255, 255, 255, 153));
        loginBox.setBorder(new LineBorder(new Color(180, 160, 245, 153), 4, true));
        loginBox.setBounds(140, 200, 800, 700);
        loginBox.setLayout(null);
        background.add(loginBox);

        // Full name field
        JTextField fullNameField = new RoundedTextField(30);
        fullNameField.setFont(new Font("Francois One", Font.PLAIN, 36));
        fullNameField.setForeground(Color.WHITE);
        fullNameField.setBackground(new Color(180, 160, 245, 153)); // #b4a0f5 60%
        fullNameField.setBorder(new LineBorder(Color.WHITE, 3, true));
        fullNameField.setBounds(50, 50, 700, 100);
        loginBox.add(fullNameField);

        // Password field
        JPasswordField passwordField = new RoundedPasswordField(30);
        passwordField.setFont(new Font("Francois One", Font.PLAIN, 36));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(new Color(118, 140, 233, 153)); // #768ce9 60%
        passwordField.setBorder(new LineBorder(Color.WHITE, 3, true));
        passwordField.setBounds(50, 200, 700, 100);
        loginBox.add(passwordField);

        // Buttons with rounded corners
        JButton loginBtn = new RoundedButton("Login", 30);
        loginBtn.setFont(new Font("Francois One", Font.PLAIN, 36));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(149, 204, 142, 153)); // #95cc8e 60%
        loginBtn.setBounds(140, 950, 800, 120);
        loginBtn.setFocusPainted(false);
        background.add(loginBtn);

        JButton registerBtn = new RoundedButton("Register", 30);
        registerBtn.setFont(new Font("Francois One", Font.PLAIN, 36));
        registerBtn.setForeground(new Color(149, 204, 142)); // #95cc8e text
        registerBtn.setBackground(new Color(255, 255, 255, 153)); // White 60% opacity
        registerBtn.setBounds(140, 1120, 800, 120);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(new LineBorder(new Color(149, 204, 142, 153), 3, true));
        background.add(registerBtn);

        // Button actions
        loginBtn.addActionListener(e -> showLoginDialog(fullNameField, passwordField));
        registerBtn.addActionListener(e -> showRegisterDialog());
    }

    private void showLoginDialog(JTextField fullNameField, JPasswordField passwordField) {
        String fullName = fullNameField.getText();
        String password = new String(passwordField.getPassword());
        if (AuthService.login(fullName, password)) {
            User user = AuthService.getUser(fullName);
            new DashboardGUI(user);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }

    private void showRegisterDialog() {
        JTextField nameField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField birthdayField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"M","F","Other"});

        Object[] message = {
            "Full Name:", nameField,
            "Password:", passField,
            "Email:", emailField,
            "Birthday:", birthdayField,
            "Gender:", genderBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            User user = new User();
            user.fullName = nameField.getText();
            user.email = emailField.getText();
            user.birthday = birthdayField.getText();
            user.gender = (String) genderBox.getSelectedItem();
            String password = new String(passField.getPassword());

            if (AuthService.register(user, password)) {
                JOptionPane.showMessageDialog(this, "Account created! You can now log in.");
            } else {
                JOptionPane.showMessageDialog(this, "Full Name already exists.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginOrRegisterGUI().setVisible(true));
    }

    // Custom panel with rounded corners
    static class RoundedPanel extends JPanel {
        private int radius;
        private Color bgColor;

        RoundedPanel(int radius, Color bgColor) {
            super();
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    // Rounded JTextField
    static class RoundedTextField extends JTextField {
        private int radius;
        RoundedTextField(int radius) { this.radius = radius; setOpaque(false); }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    // Rounded JPasswordField
    static class RoundedPasswordField extends JPasswordField {
        private int radius;
        RoundedPasswordField(int radius) { this.radius = radius; setOpaque(false); }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    // Rounded JButton
    static class RoundedButton extends JButton {
        private int radius;
        RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }
}
