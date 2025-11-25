package dao;

import db.DBConnection;
import models.Customer;
import models.User;

import java.sql.*;

public class UserDAO {

    public boolean createCustomer(String username, String email, String passwordHash) throws SQLException {
        String sql = "INSERT INTO users(username,email,password_hash) VALUES (?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, passwordHash);
            return ps.executeUpdate() > 0;
        }
    }

    public User validateUser(String username, String passwordHash) throws SQLException {
        String sql = "SELECT id,username,email FROM users WHERE username=? AND password_hash=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username); ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Customer(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
                return null;
            }
        }
    }
}
