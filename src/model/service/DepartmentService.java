package model.service;

import model.entity.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService extends BaseService {
    private static final String TABLE_NAME = "Departments";

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Department department = new Department(
                    rs.getInt("department_id"),
                    rs.getString("department_name"),
                    rs.getTimestamp("created_at")
                );
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE department_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO " + TABLE_NAME + " (department_name, created_at) VALUES (?, ?)";
        try {
            int rows = executeUpdate(sql, department.getDepartmentName(), department.getCreatedAt());
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDepartment(int id, Department department) {
        String sql = "UPDATE " + TABLE_NAME + " SET department_name = ?, created_at = ? WHERE department_id = ?";
        try {
            int rows = executeUpdate(sql,
                department.getDepartmentName(),
                department.getCreatedAt(),
                id);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDepartment(int departmentId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE department_id = ?";
        try {
            int rows = executeUpdate(sql, departmentId);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
