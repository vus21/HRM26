 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class EmployeeView extends JPanel {
    private JTextField txtEmployeeId, txtFirstName, txtLastName, txtDateOfBirth, txtGender,
                       txtAddress, txtPhoneNumber, txtEmail, txtHireDate, txtEmploymentStatus,
                       txtDepartmentId, txtPositionId, txtBaseSalary, txtSalaryCoefficient;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear,btnSave, btnCance;

    public EmployeeView() {
        setLayout(new BorderLayout());

        // Tạo panel form
        JPanel formPanel = new JPanel(new GridLayout(16, 2, 10, 10));
        formPanel.add(new JLabel("Employee ID:"));
        txtEmployeeId = new JTextField(15);
        txtEmployeeId.setEditable(false); // ID should not be editable
        formPanel.add(txtEmployeeId);
        formPanel.add(new JLabel("First Name:"));
        txtFirstName = new JTextField(15);
        formPanel.add(txtFirstName);
        formPanel.add(new JLabel("Last Name:"));
        txtLastName = new JTextField(15);
        formPanel.add(txtLastName);
        formPanel.add(new JLabel("Date of Birth:"));
        txtDateOfBirth = new JTextField(15);
        formPanel.add(txtDateOfBirth);
        formPanel.add(new JLabel("Gender:"));
        txtGender = new JTextField(15);
        formPanel.add(txtGender);
        formPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField(15);
        formPanel.add(txtAddress);
        formPanel.add(new JLabel("Phone Number:"));
        txtPhoneNumber = new JTextField(15);
        formPanel.add(txtPhoneNumber);
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField(15);
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Hire Date:"));
        txtHireDate = new JTextField(15);
        formPanel.add(txtHireDate);
        formPanel.add(new JLabel("Employment Status:"));
        txtEmploymentStatus = new JTextField(15);
        txtEmploymentStatus.setEditable(false); // Default value
        formPanel.add(txtEmploymentStatus);
        formPanel.add(new JLabel("Department ID:"));
        txtDepartmentId = new JTextField(15);
        formPanel.add(txtDepartmentId);
        formPanel.add(new JLabel("Position ID:"));
        txtPositionId = new JTextField(15);
        formPanel.add(txtPositionId);
        formPanel.add(new JLabel("Base Salary:"));
        txtBaseSalary = new JTextField(15);
        formPanel.add(txtBaseSalary);
        formPanel.add(new JLabel("Salary Coefficient:"));
        txtSalaryCoefficient = new JTextField(15);
        formPanel.add(txtSalaryCoefficient);
        
        btnAdd = new JButton("Add");
        btnCance = new JButton("Cancel");
        btnSave = new JButton("Save");
        // btnCance.setVisible(false);
        
        formPanel.add(btnCance);
        formPanel.add(btnSave);
        formPanel.add(btnAdd);
        // Tạo panel button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tạo JTable
        String[] columnNames = {"ID", "First Name", "Last Name", "DOB", "Gender", "Address", "Phone", "Email",
                              "Hire Date", "Status", "Dept ID", "Pos ID", "Base Salary", "Salary Coeff", "Created At", "Updated At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtFirstName() { return txtFirstName; }
    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtDateOfBirth() { return txtDateOfBirth; }
    public JTextField getTxtGender() { return txtGender; }
    public JTextField getTxtAddress() { return txtAddress; }
    public JTextField getTxtPhoneNumber() { return txtPhoneNumber; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtHireDate() { return txtHireDate; }
    public JTextField getTxtEmploymentStatus() { return txtEmploymentStatus; }
    public JTextField getTxtDepartmentId() { return txtDepartmentId; }
    public JTextField getTxtPositionId() { return txtPositionId; }
    public JTextField getTxtBaseSalary() { return txtBaseSalary; }
    public JTextField getTxtSalaryCoefficient() { return txtSalaryCoefficient; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnCance() { return btnCance;}
}