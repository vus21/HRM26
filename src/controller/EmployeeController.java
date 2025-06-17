
package controller;

import model.entity.Department;
import model.service.DepartmentService;
import model.entity.Employee;
import model.service.EmployeeService;
import model.service.PositionService;
import view.EmployeeView;

import javax.swing.*;
import model.entity.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EmployeeController {
    private EmployeeView employeeView;
    private EmployeeService employeeService;

    private List<Department> departments;

    private List<model.entity.Position> positions;

    public EmployeeController(EmployeeView view) {
        this.employeeView = view;
        this.employeeService = new EmployeeService();
        initListeners();
        loadEmployees();
        getPositionsByDepartment();
        getAllDepartments();
    }

    private void initListeners() {
        employeeView.getComboDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPositionsByDepartment(); // gọi hàm bạn vừa sửa
            }
        });

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
                        // xu li cho combobox
                        // Set combo department
                        for (int i = 0; i < employeeView.getComboDepartment().getItemCount(); i++) {
                            Department dept = employeeView.getComboDepartment().getItemAt(i);
                            if (dept.getDepartmentId() == employee.getDepartmentId()) {
                                employeeView.getComboDepartment().setSelectedIndex(i);
                                break;
                            }
                        }
                        // Load lại danh sách position theo department đã chọn
                        getPositionsByDepartment();
                        // Set combo position
                        for (int i = 0; i < employeeView.getComboPosition().getItemCount(); i++) {
                            Position pos = employeeView.getComboPosition().getItemAt(i);
                            if (pos.getPositionId() == employee.getPositionId()) {
                                employeeView.getComboPosition().setSelectedIndex(i);
                                break;
                            }
                        }

                        employeeView.getTxtBaseSalary().setText(String.valueOf(employee.getBaseSalary()));
                        employeeView.getTxtSalaryCoefficient().setText(String.valueOf(employee.getSalaryCoefficient()));
                        employeeView.getTxtCreatedAt().setText(employee.getCreatedAt().toString());
                        employeeView.getTxtUpdatedAt().setText(employee.getUpdatedAt().toString());
                    }
                }
            }
        });
        employeeView.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Employee employee = createEmployeeFromView();
                    employeeService.addEmployee(employee); // Có thể ném lỗi tại đây

                    clearFields();
                    loadEmployees();
                    JOptionPane.showMessageDialog(null, "Employee added successfully!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please check your data.");
                } 
                catch (RuntimeException ex) {
                    // JOptionPane.showMessageDialog(null, "Failed to add employee!\n" + ex.getMessage());
                }
            }
        });
        employeeView.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(employeeView.getTxtEmployeeId().getText());

                    // Xác nhận người dùng trước khi cập nhật (tùy chọn)
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to update employee ID " + employeeId + "?",
                            "Confirm Update",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm != JOptionPane.YES_OPTION)
                        return;

                    Employee employee = createEmployeeFromView();
                    employeeService.updateEmployee(employeeId, employee);

                    clearFields();
                    loadEmployees();
                    JOptionPane.showMessageDialog(null, "Employee updated successfully!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid employee ID or input!");
                } catch (RuntimeException ex) {
                    // Lỗi logic từ tầng Service (ví dụ: employee không tồn tại, lỗi SQL, v.v.)
                    // JOptionPane.showMessageDialog(null, "Failed to update employee:\n" +
                    // ex.getMessage());
                }
            }
        });

        employeeView.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(employeeView.getTxtEmployeeId().getText());

                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete employee ID " + employeeId + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        employeeService.deleteEmployee(employeeId);
                        clearFields();
                        loadEmployees();
                        JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid employee ID!");
                } 
                // catch (RuntimeException ex) {
                //     // Trường hợp không tìm thấy nhân viên hoặc lỗi SQL
                //     JOptionPane.showMessageDialog(null, "Failed to delete employee:\n" + ex.getMessage());
                // }
            }
        });

        employeeView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

   
// load danh sách department vào combo box
    public void loadDepartments(List<Department> departments) {
        employeeView.getComboDepartment().removeAllItems();
        for (Department dept : departments) {
            employeeView.getComboDepartment().addItem(dept);
        }
    }
// lay danh sách department từ service
    public void getAllDepartments() {
        DepartmentService departmentService = new DepartmentService();
        departments = departmentService.getAllDepartments();
        loadDepartments(departments);
    }
// load danh sách vị trí vào combo box
    public void loadPositions(List<model.entity.Position> positions) {
        employeeView.getComboPosition().removeAllItems();
        for (model.entity.Position pos : positions) {
            employeeView.getComboPosition().addItem(pos);
        }
    }
// lay danh sách vị trí theo department đã chọn
    public void getPositionsByDepartment() {
        Department selectedDepartment = (Department) employeeView.getComboDepartment().getSelectedItem();
        if (selectedDepartment != null) {
            int departmentId = selectedDepartment.getDepartmentId();
            PositionService positionService = new PositionService();
            positions = positionService.getPositionsByDepartmentId(departmentId);
            loadPositions(positions);
        } else {
            // Nếu không có department nào được chọn, xóa combo position
            employeeView.getComboPosition().removeAllItems();
        }
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
        employeeView.getTxtBaseSalary().setText("");
        employeeView.getTxtSalaryCoefficient().setText("");
    }
    private String getNonEmpty(String text, String fieldName) {
    if (text == null || text.trim().isEmpty()) {
        throw new IllegalArgumentException(fieldName + " cannot be empty.");
    }
    return text.trim();
}

private Date parseDate(String text, String fieldName) {
    try {
        return Date.valueOf(text.trim());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(fieldName + " must be in format yyyy-[m]m-[d]d.");
    }
}

private double parseDouble(String text, String fieldName) {
    try {
        return Double.parseDouble(text.trim());
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException(fieldName + " must be a valid number.");
    }
}

private int parseIntOrDefault(String text, int defaultValue) {
    try {
        return Integer.parseInt(text.trim());
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}
private Employee createEmployeeFromView() {
    try {
        int employeeId = parseIntOrDefault(employeeView.getTxtEmployeeId().getText(), 0);
        
        String firstName = getNonEmpty(employeeView.getTxtFirstName().getText(), "First Name");
        String lastName = getNonEmpty(employeeView.getTxtLastName().getText(), "Last Name");
        Date dateOfBirth = parseDate(employeeView.getTxtDateOfBirth().getText(), "Date of Birth");
        
        String gender = getNonEmpty(employeeView.getTxtGender().getText(), "Gender");
        String address = getNonEmpty(employeeView.getTxtAddress().getText(), "Address");
        String phoneNumber = getNonEmpty(employeeView.getTxtPhoneNumber().getText(), "Phone Number");
        String email = getNonEmpty(employeeView.getTxtEmail().getText(), "Email");
        // mail
         for (int i = 0; i < employeeView.getTableModel().getRowCount(); i++) {
        String existingEmail = (String) employeeView.getTableModel().getValueAt(i, 7); // cột 7 là Email
        if (existingEmail != null && existingEmail.equalsIgnoreCase(email)) {
            JOptionPane.showMessageDialog(null, "Email already exists in the table!", "Duplicate Email", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
        Date hireDate = parseDate(employeeView.getTxtHireDate().getText(), "Hire Date");
        String employmentStatus = getNonEmpty(employeeView.getTxtEmploymentStatus().getText(), "Employment Status");

        Department selectedDepartment = (Department) employeeView.getComboDepartment().getSelectedItem();
        if (selectedDepartment == null) {
            throw new IllegalArgumentException("Please select a Department.");
        }
        Integer departmentId = selectedDepartment.getDepartmentId();

        Position selectedPosition = (Position) employeeView.getComboPosition().getSelectedItem();
        if (selectedPosition == null) {
            throw new IllegalArgumentException("Please select a Position.");
        }
        Integer positionId = selectedPosition.getPositionId();

        double baseSalary = parseDouble(employeeView.getTxtBaseSalary().getText(), "Base Salary");
        double salaryCoefficient = parseDouble(employeeView.getTxtSalaryCoefficient().getText(), "Salary Coefficient");

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        return new Employee(employeeId, firstName, lastName, dateOfBirth, gender, address,
                phoneNumber, email, hireDate, employmentStatus, departmentId, positionId,
                baseSalary, salaryCoefficient, createdAt, updatedAt);

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}

}