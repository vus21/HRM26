package model.service;

import model.entity.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends BaseService {
    private static final String TABLE_NAME = "Employees";

   public void addEmployee(Employee employee) {
    String sql = "INSERT INTO " + TABLE_NAME + " (first_name, last_name, date_of_birth, gender, address, " +
            "phone_number, email, hire_date, department_id, position_id, base_salary, salary_coefficient) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        int count = executeUpdate(sql,
                employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth(),
                employee.getGender(), employee.getAddress(), employee.getPhoneNumber(), employee.getEmail(),
                employee.getHireDate(), employee.getDepartmentId(),
                employee.getPositionId(), employee.getBaseSalary(), employee.getSalaryCoefficient());

        if (count == 0) {
            throw new SQLException("No rows inserted.");
        }

    } catch (SQLException e) {
        // Ném lại lỗi để GUI xử lý
        throw new RuntimeException("Failed to add employee: " + e.getMessage(), e);
    }
}


    public void updateEmployee(int employeeId, Employee employee) {
    String sql = "UPDATE " + TABLE_NAME
            + " SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, address = ?, "
            + "phone_number = ?, email = ?, hire_date = ?, employment_status = ?, department_id = ?, position_id = ?, "
            + "base_salary = ?, salary_coefficient = ?, updated_at = ? WHERE employee_id = ?";

    try {
        int count = executeUpdate(sql,
                employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth(), employee.getGender(),
                employee.getAddress(), employee.getPhoneNumber(), employee.getEmail(), employee.getHireDate(),
                employee.getEmploymentStatus(), employee.getDepartmentId(), employee.getPositionId(),
                employee.getBaseSalary(), employee.getSalaryCoefficient(), employee.getUpdatedAt(), employeeId);

        if (count == 0) {
            throw new SQLException("No employee found with ID: " + employeeId);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Failed to update employee: " + e.getMessage(), e);
    }
}


    public void deleteEmployee(int employeeId) {
    String sql = "DELETE FROM " + TABLE_NAME + " WHERE employee_id = ?";
    try {
        executeUpdate(sql, employeeId);
    } catch (SQLException e) {
        throw new RuntimeException("Failed to delete employee: " + e.getMessage(), e);
    }
}


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("hire_date"),
                        rs.getString("employment_status"),
                        rs.getObject("department_id") != null ? rs.getInt("department_id") : null,
                        rs.getObject("position_id") != null ? rs.getInt("position_id") : null,
                        rs.getDouble("base_salary"),
                        rs.getDouble("salary_coefficient"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
                employees.add(employee);
            }
            System.out.println("Number of employees loaded: " + employees.size()); // Debug

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage()); // Debug
        }
        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE employee_id = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("hire_date"),
                        rs.getString("employment_status"),
                        rs.getObject("department_id") != null ? rs.getInt("department_id") : null,
                        rs.getObject("position_id") != null ? rs.getInt("position_id") : null,
                        rs.getDouble("base_salary"),
                        rs.getDouble("salary_coefficient"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}