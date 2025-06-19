package model.service;

import model.entity.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService extends BaseService {
    private static final String TABLE_NAME = "Attendance";

    public List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY attendance_id DESC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Attendance getAttendanceById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE attendance_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addAttendance(Attendance att) {
        String sql = "INSERT INTO " + TABLE_NAME + " (employee_id, attendance_date, check_in_time, " +
                     "check_out_time, status, work_hours) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, att.getEmployeeId());
            stmt.setDate(2, att.getAttendanceDate());
            stmt.setTime(3, att.getCheckInTime());
            stmt.setTime(4, att.getCheckOutTime());
            stmt.setString(5, att.getStatus());
            stmt.setDouble(6, att.getWorkHours());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAttendance(int id, Attendance att) {
        String sql = "UPDATE " + TABLE_NAME + " SET employee_id = ?, attendance_date = ?, " +
                     "check_in_time = ?, check_out_time = ?, status = ?, work_hours = ? WHERE attendance_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, att.getEmployeeId());
            stmt.setDate(2, att.getAttendanceDate());
            stmt.setTime(3, att.getCheckInTime());
            stmt.setTime(4, att.getCheckOutTime());
            stmt.setString(5, att.getStatus());
            stmt.setDouble(6, att.getWorkHours());
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAttendance(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE attendance_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper: Convert ResultSet → Attendance
    private Attendance mapResultSet(ResultSet rs) throws SQLException {
        return new Attendance(
            rs.getInt("attendance_id"),
            rs.getInt("employee_id"),
            rs.getDate("attendance_date"),
            rs.getTime("check_in_time"),
            rs.getTime("check_out_time"),
            rs.getString("status"),
            rs.getDouble("work_hours"),
            rs.getTimestamp("created_at")
        );
    }
}
