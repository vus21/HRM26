package model.entity;

import java.sql.Timestamp;

public class Department {
    private int departmentId;
    private String departmentName;
    private Timestamp createdAt;

    public Department(int departmentId, String departmentName, Timestamp createdAt) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.createdAt = createdAt;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}