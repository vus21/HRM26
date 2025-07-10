package model.service;

import model.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends BaseService{

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setEmployeeId(rs.getInt("employee_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setRole(rs.getString("role"));
                u.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
public List<String> getAllRoles(){
        List<String> roles = new ArrayList<>();
        String sql = "SELECT DISTINCT role FROM Users";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                roles.add(rs.getString("role"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();}
            return roles;
}
    public void insertUser(User user) {
        String sql = "INSERT INTO Users (employee_id, username, password_hash, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getEmployeeId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql;
        if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
            sql = "UPDATE Users SET employee_id=?, username=?, password_hash=?, role=? WHERE user_id=?";
        } else {
            sql = "UPDATE Users SET employee_id=?, username=?, role=? WHERE user_id=?";
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getEmployeeId());
            stmt.setString(2, user.getUsername());

            if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
                stmt.setString(3, user.getPasswordHash());
                stmt.setString(4, user.getRole());
                stmt.setInt(5, user.getUserId());
            } else {
                stmt.setString(3, user.getRole());
                stmt.setInt(4, user.getUserId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
