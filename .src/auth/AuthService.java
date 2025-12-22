package auth;

import database.Database;
import model.User;

import java.sql.*;

public class AuthService {

    public static boolean register(User user, String password) {
        String hashed = PasswordUtil.hash(password);
        String sql = "INSERT INTO users (full_name, password_hash, email, age, gender) VALUES (?, ?, ?, ?, ?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user.fullName);
            ps.setString(2, hashed);
            ps.setString(3, user.email);
            ps.setInt(4, user.age);
            ps.setString(5, user.gender);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false; 
        }
    }

    public static boolean login(String fullName, String password) {
        String sql = "SELECT password_hash FROM users WHERE full_name = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return PasswordUtil.verify(password, rs.getString("password_hash"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUser(String fullName) {
        String sql = "SELECT * FROM users WHERE full_name = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.fullName = fullName;
                user.email = rs.getString("email");
                user.age = rs.getInt("age");
                user.gender = rs.getString("gender");
                user.passwordHash = rs.getString("password_hash");
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
