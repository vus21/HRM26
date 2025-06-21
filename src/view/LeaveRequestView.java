package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LeaveRequestView extends JPanel {
    private JTextField txtLeaveId,txtEmployeeName, txtEmployeeId, txtStartDate, txtEndDate, txtCreatedAt;
    private JComboBox<String> comboLeaveType, comboStatus;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnApprove, btnReject, btnClear,btnDelete, btnCancel;

    public LeaveRequestView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel title = new JLabel("Leave Request Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Leave Request Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // ID
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Leave ID:"), gbc);
        gbc.gridx = 1;
        txtLeaveId = new JTextField("0", 15);
        txtLeaveId.setEditable(false);
        formPanel.add(txtLeaveId, gbc);

        // Employee ID
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        txtEmployeeId = new JTextField(15);
        txtEmployeeId.setEditable(false);
        formPanel.add(txtEmployeeId, gbc);

        // Employee Name
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Employee Name:"), gbc);
        gbc.gridx = 1;
        txtEmployeeName = new JTextField(15);
        txtEmployeeName.setEditable(false);
        formPanel.add(txtEmployeeName, gbc);

        // Start Date
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Start Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        txtStartDate = new JTextField(15);
        txtStartDate.setEditable(false);
        formPanel.add(txtStartDate, gbc);

        // End Date
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("End Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        txtEndDate = new JTextField(15);
        txtEndDate.setEditable(false);
        formPanel.add(txtEndDate, gbc);

        // Leave Type
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Leave Type:"), gbc);
        gbc.gridx = 1;
        comboLeaveType = new JComboBox<>(new String[]{"Annual", "Sick"});
        comboLeaveType.setEnabled(false);
        formPanel.add(comboLeaveType, gbc);

        // Status
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        comboStatus = new JComboBox<>(new String[]{"Pending", "Approved", "Rejected"});
        comboStatus.setEnabled(false);
        formPanel.add(comboStatus, gbc);

        // Created At
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Created At:"), gbc);
        gbc.gridx = 1;
        txtCreatedAt = new JTextField(15);
        txtCreatedAt.setEditable(false);
        formPanel.add(txtCreatedAt, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnApprove = createButton("Approve");
        btnReject = createButton("Reject");
        btnClear = createButton("Clear");
        btnDelete = createButton("Delete");
        btnCancel = createButton("Cancel");

        buttonPanel.add(btnApprove);
        buttonPanel.add(btnReject);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnCancel);

        // Table
        String[] columns = {"ID", "Employee", "Start", "End", "Type", "Status", "Remaining", "Created"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        add(formPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    // Getters
    public JTextField getTxtLeaveId() { return txtLeaveId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtEmployeeName() { return txtEmployeeName; }
    public JTextField getTxtStartDate() { return txtStartDate; }
    public JTextField getTxtEndDate() { return txtEndDate; }
    public JTextField getTxtCreatedAt() { return txtCreatedAt; }
    public JComboBox<String> getComboLeaveType() { return comboLeaveType; }
    public JComboBox<String> getComboStatus() { return comboStatus; }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public JButton getBtnApprove() { return btnApprove; }
    public JButton getBtnReject() { return btnReject; }
    public JButton getBtnClear() { return btnClear; }

    public JButton getBtnCancel() { return btnCancel; }
    public JButton getBtnDelete() { return btnDelete;}
}
