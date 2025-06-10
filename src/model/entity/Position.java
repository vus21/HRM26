package model.entity;

import java.sql.Timestamp;

public class Position {
    private int positionId;
    private String positionName;
    private int departmentId;
    private Timestamp createdAt;

    public Position(int positionId, String positionName, int departmentId, Timestamp createdAt) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.departmentId = departmentId;
        this.createdAt = createdAt;
    }

    public int getPositionId() {
        return positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}