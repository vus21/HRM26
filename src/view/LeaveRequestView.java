 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class LeaveRequestView extends JPanel {
    private JTextField txtLeaveId, txtEmployeeId, txtStartDate, txtEndDate, txtLeaveType,
                       txtStatus, txtRemainingLeaveDays;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public LeaveRequestView() {
        setLayout(new BorderLayout());

        // Tạo panel form
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.add(new JLabel("Leave ID:"));
        txtLeaveId = new JTextField(15);
        formPanel.add(txtLeaveId);
        formPanel.add(new JLabel("Employee ID:"));
        txtEmployeeId = new JTextField(15);
        formPanel.add(txtEmployeeId);
        formPanel.add(new JLabel("Start Date:"));
        txtStartDate = new JTextField(15);
        formPanel.add(txtStartDate);
        formPanel.add(new JLabel("End Date:"));
        txtEndDate = new JTextField(15);
        formPanel.add(txtEndDate);
        formPanel.add(new JLabel("Leave Type:"));
        txtLeaveType = new JTextField(15);
        formPanel.add(txtLeaveType);
        formPanel.add(new JLabel("Status:"));
        txtStatus = new JTextField(15);
        formPanel.add(txtStatus);
        formPanel.add(new JLabel("Remaining Leave Days:"));
        txtRemainingLeaveDays = new JTextField(15);
        formPanel.add(txtRemainingLeaveDays);

        // Tạo panel button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tạo JTable
        String[] columnNames = {"ID", "Employee ID", "Start Date", "End Date", "Leave Type", "Status", "Remaining Days", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtLeaveId() { return txtLeaveId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtStartDate() { return txtStartDate; }
    public JTextField getTxtEndDate() { return txtEndDate; }
    public JTextField getTxtLeaveType() { return txtLeaveType; }
    public JTextField getTxtStatus() { return txtStatus; }
    public JTextField getTxtRemainingLeaveDays() { return txtRemainingLeaveDays; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}