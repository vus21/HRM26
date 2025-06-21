 
package model.service;

import model.entity.LeaveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestService extends BaseService {
    private static final String TABLE_NAME = "Leave_Requests";

    // Lấy tất cả đơn nghỉ phép
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY created_at DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest(
                    rs.getInt("leave_id"),
                    rs.getInt("employee_id"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("leave_type"),
                    rs.getString("status"),
                    rs.getInt("remaining_leave_days"),
                    rs.getTimestamp("created_at")
                );
                list.add(lr);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching leave requests", e);
        }

        return list;
    }

    // Thêm đơn nghỉ phép mới
    public void addLeaveRequest(LeaveRequest request) {
        String sql = "INSERT INTO " + TABLE_NAME + " (employee_id, start_date, end_date, leave_type, status, remaining_leave_days, created_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, request.getEmployeeId());
            pstmt.setDate(2, request.getStartDate());
            pstmt.setDate(3, request.getEndDate());
            pstmt.setString(4, request.getLeaveType());
            pstmt.setString(5, request.getStatus());
            pstmt.setInt(6, request.getRemainingLeaveDays());
            pstmt.setTimestamp(7, request.getCreatedAt());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding leave request", e);
        }
    }

    // Cập nhật trạng thái đơn nghỉ phép
   public void updateLeaveStatus(int leaveId, String newStatus) {
    String updateSql = "UPDATE " + TABLE_NAME + " SET status = ? WHERE leave_id = ?";
    String selectSql = "SELECT leave_type, start_date, end_date, remaining_leave_days FROM " + TABLE_NAME + " WHERE leave_id = ?";

    try (Connection conn = getConnection()) {
        conn.setAutoCommit(false); // Bắt đầu transaction

        String leaveType = null;
        Date startDate = null, endDate = null;
        int remainingDays = 0;

        // Lấy thông tin đơn nghỉ
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, leaveId);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                leaveType = rs.getString("leave_type");
                startDate = rs.getDate("start_date");
                endDate = rs.getDate("end_date");
                remainingDays = rs.getInt("remaining_leave_days");
            } else {
                throw new SQLException("Leave request not found");
            }
        }

        // Nếu là Sick + Approved thì trừ số ngày phép
        if ("Sick".equalsIgnoreCase(leaveType) && "Approved".equalsIgnoreCase(newStatus)) {
            int daysOff = getDaysBetween(startDate, endDate);
            remainingDays = Math.max(0, remainingDays - daysOff);

            // Cập nhật lại số ngày còn lại
            String updateDaysSql = "UPDATE " + TABLE_NAME + " SET remaining_leave_days = ? WHERE leave_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateDaysSql)) {
                pstmt.setInt(1, remainingDays);
                pstmt.setInt(2, leaveId);
                pstmt.executeUpdate();
            }
        }

        // Cập nhật trạng thái
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, leaveId);
            updateStmt.executeUpdate();
        }

        conn.commit();

    } catch (SQLException e) {
        throw new RuntimeException("Error updating leave status", e);
    }
}

// ✅ Hàm phụ tính số ngày nghỉ
private int getDaysBetween(Date start, Date end) {
    long diffMillis = end.getTime() - start.getTime();
    return (int) (diffMillis / (1000 * 60 * 60 * 24)) + 1; // +1 để tính luôn ngày bắt đầu
}

    // Xóa đơn nghỉ phép
    public void deleteLeaveRequest(int leaveId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE leave_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, leaveId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting leave request", e);
        }
    }

    // Lấy đơn nghỉ phép theo employee_id
    public List<LeaveRequest> getRequestsByEmployeeId(int employeeId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE employee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest lr = new LeaveRequest(
                        rs.getInt("leave_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("leave_type"),
                        rs.getString("status"),
                        rs.getInt("remaining_leave_days"),
                        rs.getTimestamp("created_at")
                    );
                    list.add(lr);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching leave requests by employee", e);
        }

        return list;
    }
}
