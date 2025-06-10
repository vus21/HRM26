 
package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Payroll {
    private int payrollId;
    private int employeeId;
    private Date payPeriod;
    private double baseSalary;
    private double allowance;
    private double bonus;
    private double taxDeduction;
    private double netSalary;
    private String notes;
    private Timestamp createdAt;

    public Payroll(int payrollId, int employeeId, Date payPeriod, double baseSalary, double allowance,
                   double bonus, double taxDeduction, double netSalary, String notes, Timestamp createdAt) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.payPeriod = payPeriod;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.bonus = bonus;
        this.taxDeduction = taxDeduction;
        this.netSalary = netSalary;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getPayPeriod() {
        return payPeriod;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public double getBonus() {
        return bonus;
    }

    public double getTaxDeduction() {
        return taxDeduction;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public String getNotes() {
        return notes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setPayPeriod(Date payPeriod) {
        this.payPeriod = payPeriod;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setTaxDeduction(double taxDeduction) {
        this.taxDeduction = taxDeduction;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}