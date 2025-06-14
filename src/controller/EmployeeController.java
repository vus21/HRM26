
package controller;

import model.entity.Employee;
import model.service.EmployeeService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EmployeeController {
    private EmployeeView employeeView;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeView view) {
        this.employeeView = view;
        this.employeeService = new EmployeeService();
        initListeners();
        loadEmployees();
    }

    private void initListeners() {
        // Thêm MouseListener cho JTable
        employeeView.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = employeeView.getTable().getSelectedRow();
                if (row >= 0) {
                    int employeeId = (int) employeeView.getTable().getValueAt(row, 0);
                    Employee employee = employeeService.getEmployeeById(employeeId);
                    if (employee != null) {
                        employeeView.getTxtEmployeeId().setText(String.valueOf(employee.getEmployeeId()));
                        employeeView.getTxtFirstName().setText(employee.getFirstName());
                        employeeView.getTxtLastName().setText(employee.getLastName());
                        employeeView.getTxtDateOfBirth()
                                .setText(employee.getDateOfBirth() != null ? employee.getDateOfBirth().toString() : "");
                        employeeView.getTxtGender().setText(employee.getGender());
                        employeeView.getTxtAddress().setText(employee.getAddress());
                        employeeView.getTxtPhoneNumber().setText(employee.getPhoneNumber());
                        employeeView.getTxtEmail().setText(employee.getEmail());
                        employeeView.getTxtHireDate()
                                .setText(employee.getHireDate() != null ? employee.getHireDate().toString() : "");
                        employeeView.getTxtEmploymentStatus().setText(employee.getEmploymentStatus());
                        employeeView.getTxtDepartmentId().setText(
                                employee.getDepartmentId() != null ? String.valueOf(employee.getDepartmentId()) : "");
                        employeeView.getTxtPositionId().setText(
                                employee.getPositionId() != null ? String.valueOf(employee.getPositionId()) : "");
                        employeeView.getTxtBaseSalary().setText(String.valueOf(employee.getBaseSalary()));
                        employeeView.getTxtSalaryCoefficient().setText(String.valueOf(employee.getSalaryCoefficient()));
                    }
                }
            }
        });
        employeeView.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Employee employee = createEmployeeFromView();
                    employeeService.addEmployee(employee);
                    clearFields();
                    loadEmployees();
                    JOptionPane.showMessageDialog(null, "Employee added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please check your data.");
                }
            }
        });

        employeeView.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(employeeView.getTxtEmployeeId().getText());
                    Employee employee = createEmployeeFromView();
                    employeeService.updateEmployee(employeeId, employee);
                    clearFields();
                    loadEmployees();
                    JOptionPane.showMessageDialog(null, "Employee updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid employee ID or input!");
                }
            }
        });

        employeeView.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(employeeView.getTxtEmployeeId().getText());
                    employeeService.deleteEmployee(employeeId);
                    clearFields();
                    loadEmployees();
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid employee ID!");
                }
            }
        });

        employeeView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private Employee createEmployeeFromView() {
        int employeeId = Integer.parseInt(employeeView.getTxtEmployeeId().getText());
        String firstName = employeeView.getTxtFirstName().getText();
        String lastName = employeeView.getTxtLastName().getText();
        Date dateOfBirth = Date.valueOf(employeeView.getTxtDateOfBirth().getText());
        String gender = employeeView.getTxtGender().getText();
        String address = employeeView.getTxtAddress().getText();
        String phoneNumber = employeeView.getTxtPhoneNumber().getText();
        String email = employeeView.getTxtEmail().getText();
        Date hireDate = Date.valueOf(employeeView.getTxtHireDate().getText());
        String employmentStatus = employeeView.getTxtEmploymentStatus().getText();
        Integer departmentId = employeeView.getTxtDepartmentId().getText().isEmpty() ? null
                : Integer.parseInt(employeeView.getTxtDepartmentId().getText());
        Integer positionId = employeeView.getTxtPositionId().getText().isEmpty() ? null
                : Integer.parseInt(employeeView.getTxtPositionId().getText());
        double baseSalary = Double.parseDouble(employeeView.getTxtBaseSalary().getText());
        double salaryCoefficient = Double.parseDouble(employeeView.getTxtSalaryCoefficient().getText());
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        return new Employee(employeeId, firstName, lastName, dateOfBirth, gender, address, phoneNumber, email,
                hireDate, employmentStatus, departmentId, positionId, baseSalary, salaryCoefficient, createdAt,
                updatedAt);
    }

    private void loadEmployees() {
        employeeView.getTableModel().setRowCount(0);
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("Number of employees loaded: " + employees.size()); // Debug
        for (Employee emp : employees) {
            employeeView.getTableModel().addRow(new Object[] {
                    emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), emp.getDateOfBirth(),
                    emp.getGender(), emp.getAddress(), emp.getPhoneNumber(), emp.getEmail(), emp.getHireDate(),
                    emp.getEmploymentStatus(), emp.getDepartmentId(), emp.getPositionId(), emp.getBaseSalary(),
                    emp.getSalaryCoefficient(), emp.getCreatedAt(), emp.getUpdatedAt()
            });
        }
    }

    private void clearFields() {
        employeeView.getTxtEmployeeId().setText("");
        employeeView.getTxtFirstName().setText("");
        employeeView.getTxtLastName().setText("");
        employeeView.getTxtDateOfBirth().setText("");
        employeeView.getTxtGender().setText("");
        employeeView.getTxtAddress().setText("");
        employeeView.getTxtPhoneNumber().setText("");
        employeeView.getTxtEmail().setText("");
        employeeView.getTxtHireDate().setText("");
        employeeView.getTxtEmploymentStatus().setText("");
        employeeView.getTxtDepartmentId().setText("");
        employeeView.getTxtPositionId().setText("");
        employeeView.getTxtBaseSalary().setText("");
        employeeView.getTxtSalaryCoefficient().setText("");
    }
}