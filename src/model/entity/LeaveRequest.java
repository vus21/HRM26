 
package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class LeaveRequest {
    private int leaveId;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String leaveType;
    private String status;
    private int remainingLeaveDays;
    private Timestamp createdAt;

    public LeaveRequest(int leaveId, int employeeId, Date startDate, Date endDate, String leaveType,
                        String status, int remainingLeaveDays, Timestamp createdAt) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = status;
        this.remainingLeaveDays = remainingLeaveDays;
        this.createdAt = createdAt;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStatus() {
        return status;
    }

    public int getRemainingLeaveDays() {
        return remainingLeaveDays;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemainingLeaveDays(int remainingLeaveDays) {
        this.remainingLeaveDays = remainingLeaveDays;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}