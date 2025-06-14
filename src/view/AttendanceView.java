 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AttendanceView extends JPanel {
    private JTextField txtAttendanceId, txtEmployeeId, txtAttendanceDate, txtCheckInTime,
                       txtCheckOutTime, txtStatus, txtWorkHours;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public AttendanceView() {
        setLayout(new BorderLayout());

        // Tạo panel form
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.add(new JLabel("Attendance ID:"));
        txtAttendanceId = new JTextField(15);
        formPanel.add(txtAttendanceId);
        formPanel.add(new JLabel("Employee ID:"));
        txtEmployeeId = new JTextField(15);
        formPanel.add(txtEmployeeId);
        formPanel.add(new JLabel("Attendance Date:"));
        txtAttendanceDate = new JTextField(15);
        formPanel.add(txtAttendanceDate);
        formPanel.add(new JLabel("Check In Time:"));
        txtCheckInTime = new JTextField(15);
        txtCheckInTime.setEnabled(false); //====
        formPanel.add(txtCheckInTime);
        formPanel.add(new JLabel("Check Out Time:"));
        txtCheckOutTime = new JTextField(15);
        txtCheckOutTime.setEnabled(false); //====
        formPanel.add(txtCheckOutTime);
        formPanel.add(new JLabel("Status:"));
        txtStatus = new JTextField(15);
        formPanel.add(txtStatus);
        formPanel.add(new JLabel("Work Hours:"));
        txtWorkHours = new JTextField(15);
        formPanel.add(txtWorkHours);

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
        String[] columnNames = {"ID", "Employee ID", "Date", "Check In", "Check Out", "Status", "Work Hours", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtAttendanceId() { return txtAttendanceId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtAttendanceDate() { return txtAttendanceDate; }
    public JTextField getTxtCheckInTime() { return txtCheckInTime; }
    public JTextField getTxtCheckOutTime() { return txtCheckOutTime; }
    public JTextField getTxtStatus() { return txtStatus; }
    public JTextField getTxtWorkHours() { return txtWorkHours; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}