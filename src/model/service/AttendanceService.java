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
    public List<Attendance> getAttendanceByMonth(int year, int month) {
    List<Attendance> list = new ArrayList<>();
    String sql = "SELECT * FROM Attendance WHERE YEAR(attendance_date) = ? AND MONTH(attendance_date) = ?";
    
    try (Connection conn = getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, year);
        stmt.setInt(2, month);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Attendance a = new Attendance(
                rs.getInt("attendance_id"),
                rs.getInt("employee_id"),
                rs.getDate("attendance_date"),
                rs.getTime("check_in_time"),
                rs.getTime("check_out_time"),
                rs.getString("status"),
                rs.getDouble("work_hours"),
                rs.getTimestamp("created_at")
            );
            list.add(a);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
public boolean exists(int empId, Date date) {
    String sql = "SELECT COUNT(*) FROM Attendance WHERE employee_id = ? AND attendance_date = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, empId);
        ps.setDate(2, date);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1) > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public void updateByEmployeeAndDate(int empId, Date date, Attendance a) {
    String sql = "UPDATE Attendance SET check_in_time=?, check_out_time=?, status=?, work_hours=?, created_at=? WHERE employee_id=? AND attendance_date=?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setTime(1, a.getCheckInTime());
        ps.setTime(2, a.getCheckOutTime());
        ps.setString(3, a.getStatus());
        ps.setDouble(4, a.getWorkHours());
        ps.setTimestamp(5, a.getCreatedAt());
        ps.setInt(6, empId);
        ps.setDate(7, date);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Failed to update attendance", e);
    }
}

public void deleteByEmployeeAndDate(int empId, Date date) {
    String sql = "DELETE FROM Attendance WHERE employee_id = ? AND attendance_date = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, empId);
        ps.setDate(2, date);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Failed to delete attendance", e);
    }
}
public Attendance getAttendanceByEmployeeAndDate(int empId, Date date) {
    String sql = "SELECT * FROM Attendance WHERE employee_id = ? AND attendance_date = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, empId);
        ps.setDate(2, date);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return mapResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}