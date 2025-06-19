package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AttendanceView extends JPanel {
    private JTextField txtAttendanceId, txtEmployeeId, txtAttendanceDate, txtCheckInTime, txtCheckOutTime,
                       txtWorkHours, txtCreatedAt;
    private JComboBox<String> comboStatus, comboEmployee;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnCancel;

    public AttendanceView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Attendance Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Attendance Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // attendance_id
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Attendance ID:"), gbc);
        gbc.gridx = 1;
        txtAttendanceId = new JTextField("0", 15);
        formPanel.add(txtAttendanceId, gbc);

        // employee_id
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        // txtEmployeeId = new JTextField(15);
        // formPanel.add(txtEmployeeId, gbc);
        comboEmployee = new JComboBox<>();
comboEmployee.setPreferredSize(new Dimension(200, 25));
formPanel.add(comboEmployee, gbc);

        // attendance_date
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        txtAttendanceDate = new JTextField(15);
        formPanel.add(txtAttendanceDate, gbc);

        // check_in_time
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Check-in Time (HH:mm:ss):"), gbc);
        gbc.gridx = 1;
        txtCheckInTime = new JTextField(15);
        formPanel.add(txtCheckInTime, gbc);

        // check_out_time
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Check-out Time (HH:mm:ss):"), gbc);
        gbc.gridx = 1;
        txtCheckOutTime = new JTextField(15);
        formPanel.add(txtCheckOutTime, gbc);

        // status (combo box)
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        comboStatus = new JComboBox<>(new String[] {"OnTime", "Late", "EarlyLeave", "Absent"});
        formPanel.add(comboStatus, gbc);

        // work_hours
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Work Hours:"), gbc);
        gbc.gridx = 1;
        txtWorkHours = new JTextField(15);
        formPanel.add(txtWorkHours, gbc);

        // created_at
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Created At:"), gbc);
        gbc.gridx = 1;
        txtCreatedAt = new JTextField(15);
        formPanel.add(txtCreatedAt, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = createButton("Add");
        btnUpdate = createButton("Update");
        btnDelete = createButton("Delete");
        btnClear = createButton("Clear");
        btnCancel = createButton("Cancel");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnCancel);

        // Table
        String[] columnNames = {"ID", "Employee ID", "Date", "Check-in", "Check-out", "Status", "Hours", "Created"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add to layout
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
    public JTextField getTxtAttendanceId() { return txtAttendanceId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtAttendanceDate() { return txtAttendanceDate; }
    public JTextField getTxtCheckInTime() { return txtCheckInTime; }
    public JTextField getTxtCheckOutTime() { return txtCheckOutTime; }
    public JComboBox<String> getComboStatus() { return comboStatus; }
    public JComboBox<String> getComboEmployee() { return comboEmployee; }
    public JTextField getTxtWorkHours() { return txtWorkHours; }
    public JTextField getTxtCreatedAt() { return txtCreatedAt; }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnCancel() { return btnCancel; }
}
