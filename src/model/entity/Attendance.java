 
package model.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Attendance {
    private int attendanceId;
    private int employeeId;
    private Date attendanceDate;
    private Time checkInTime;
    private Time checkOutTime;
    private String status;
    private Double workHours;
    private Timestamp createdAt;

    public Attendance(int attendanceId, int employeeId, Date attendanceDate, Time checkInTime,
                      Time checkOutTime, String status, Double workHours, Timestamp createdAt) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.workHours = workHours;
        this.createdAt = createdAt;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public String getStatus() {
        return status;
    }

    public Double getWorkHours() {
        return workHours;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWorkHours(Double workHours) {
        this.workHours = workHours;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
}