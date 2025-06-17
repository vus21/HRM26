 
package model.entity;


import java.sql.Date;
import java.sql.Timestamp;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private Date hireDate;
    private String employmentStatus;
    private Integer departmentId; // Nullable (FK)
    private Integer positionId;   // Nullable (FK)
    private double baseSalary;
    private double salaryCoefficient;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Employee(int employeeId, String firstName, String lastName, Date dateOfBirth, String gender,
                    String address, String phoneNumber, String email, Date hireDate, String employmentStatus,
                    Integer departmentId, Integer positionId, double baseSalary, double salaryCoefficient,
                    Timestamp createdAt, Timestamp updatedAt) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hireDate = hireDate;
        this.employmentStatus = employmentStatus;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.baseSalary = baseSalary;
        this.salaryCoefficient = salaryCoefficient;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setSalaryCoefficient(double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}