import auth.LoginOrRegisterGUI;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            LoginOrRegisterGUI loginGui = new LoginOrRegisterGUI();
            loginGui.setVisible(true);
        });
    }
}
