 
package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class PerformanceEvaluation {
    private int evaluationId;
    private int employeeId;
    private Date evaluationDate;
    private double score;
    private String comments;
    private Timestamp createdAt;

    public PerformanceEvaluation(int evaluationId, int employeeId, Date evaluationDate, double score,
                                 String comments, Timestamp createdAt) {
        this.evaluationId = evaluationId;
        this.employeeId = employeeId;
        this.evaluationDate = evaluationDate;
        this.score = score;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public double getScore() {
        return score;
    }

    public String getComments() {
        return comments;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}