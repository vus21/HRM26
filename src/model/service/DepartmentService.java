 
package model.service;

import model.entity.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            System.out.println("SQL Error: " + e.getMessage());
        }
        return departments;
    }

    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE department_id = ?";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Department(
                    rs.getInt("department_id"),
                    rs.getString("department_name"),
                    rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}