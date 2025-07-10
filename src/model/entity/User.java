 
package model.entity;

import java.sql.Timestamp;

public class User {
    private int userId;
    private int employeeId;
    private String username;
    private String passwordHash;
    private String role;
    private Timestamp createdAt;
    public User(){
        // Default constructor
        this.userId = 0;
        this.employeeId = 0;
        this.username = "";
        this.passwordHash = "";
        this.role = "";
        this.createdAt = new Timestamp(System.currentTimeMillis());
        
    };
    public User(int userId, int employeeId, String username, String passwordHash, String role, Timestamp createdAt) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}