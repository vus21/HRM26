package controller;

import view.AttendanceDialog;
import view.AttendanceView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.entity.Attendance;
import model.entity.Employee;
import model.service.AttendanceService;
import model.service.EmployeeService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceController {
    private AttendanceView attendanceView;
private EmployeeService employeeService; // Giả sử bạn có service này
private AttendanceService attendanceService; // Giả sử bạn có service này
    public AttendanceController(AttendanceView view) {
        this.attendanceView = view;
        this.employeeService = new EmployeeService(); // Khởi tạo service
        this.attendanceService = new AttendanceService(); // Khởi tạo service
        initListeners();
        loadInitialData();
    }

    private void initListeners() {
        // attendanceView.getComboYear().addActionListener(e -> updateTableHeader());
        // attendanceView.getComboMonth().addActionListener(e -> updateTableHeader());
        attendanceView.getComboMonth().addActionListener(e -> reloadTable());
attendanceView.getComboYear().addActionListener(e -> reloadTable());

        attendanceView.getTable().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JTable table = attendanceView.getTable();
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();

            if (col >= 2) {
                int day = col - 1;
                int year = (int) attendanceView.getComboYear().getSelectedItem();
                int month = (int) attendanceView.getComboMonth().getSelectedItem();
                int empId = Integer.parseInt(table.getValueAt(row, 0).toString());
                String empName = table.getValueAt(row, 1).toString();
                showAttendanceDialog(empId, empName, day, month, year);
            }
        }
    });
    }
private void reloadTable() {
    int year = (int) attendanceView.getComboYear().getSelectedItem();
    int month = (int) attendanceView.getComboMonth().getSelectedItem();
    loadMonthlyAttendance(year, month);
}
private void showAttendanceDialog(int empId, String name, int day, int month, int year) {
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(attendanceView);
AttendanceDialog dialog = new AttendanceDialog(parentFrame, empId, name, day, month, year);
Attendance Nowattendance = attendanceService.getAttendanceByEmployeeAndDate(empId, Date.valueOf(String.format("%04d-%02d-%02d", year, month, day)));

    if (Nowattendance != null) {
        dialog.getComboStatus().setSelectedItem(Nowattendance.getStatus());
        if (Nowattendance.getStatus().equals("Absent")) {
            dialog.getTxtCheckIn().setText("");
            dialog.getTxtCheckOut().setText("");
        } else {
            dialog.getTxtCheckIn().setText(Nowattendance.getCheckInTime().toString());
            dialog.getTxtCheckOut().setText(Nowattendance.getCheckOutTime().toString());
        }
        
        
    } else {
        dialog.getTxtCheckIn().setText("08:00:00");
        dialog.getTxtCheckOut().setText("17:00:00");
        dialog.getComboStatus().setSelectedItem("OnTime");
    }

    // Gắn sự kiện cho nút Save
    dialog.getBtnSave().addActionListener(e -> {
        try {
            String checkInStr = dialog.getTxtCheckIn().getText();
            String checkOutStr = dialog.getTxtCheckOut().getText();
            String status = dialog.getComboStatus().getSelectedItem().toString();

            Time checkIn = Time.valueOf(checkInStr);
            Time checkOut = Time.valueOf(checkOutStr);
            double hours = (checkOut.getTime() - checkIn.getTime()) / (1000.0 * 60 * 60);
            Date date = Date.valueOf(String.format("%04d-%02d-%02d", year, month, day));
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());

            Attendance attendance = new Attendance(0, empId, date, checkIn, checkOut, status, hours, createdAt);

            if (attendanceService.exists(empId, date)) {
                attendanceService.updateByEmployeeAndDate(empId, date, attendance);
            } else {
                attendanceService.addAttendance(attendance);
            }

            JOptionPane.showMessageDialog(dialog, "Saved successfully!");
            dialog.dispose();
            reloadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
        }
    });

    // Gắn sự kiện cho nút Delete
    dialog.getBtnDelete().addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(dialog, "Delete this attendance record?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Date date = Date.valueOf(String.format("%04d-%02d-%02d", year, month, day));
            attendanceService.deleteByEmployeeAndDate(empId, date);
            dialog.dispose();
            reloadTable();
        }
    });

    dialog.setVisible(true);
}



    private void loadInitialData() {
        LocalDate today = LocalDate.now();
        attendanceView.getComboYear().setSelectedItem(today.getYear());
        attendanceView.getComboMonth().setSelectedItem(today.getMonthValue());
       reloadTable();

        // Dummy data loading example
        
    }
    public void loadMonthlyAttendance(int year, int month) {
    DefaultTableModel model = attendanceView.getTableModel();
    model.setRowCount(0); // clear table
    attendanceView.setTableHeaders(year, month); // reset header theo tháng mới

    List<Employee> employees = employeeService.getAllEmployees(); // phải có service này
    List<Attendance> records = attendanceService.getAttendanceByMonth(year, month);

    // Map attendance by employeeId and day
    Map<Integer, Map<Integer, String>> attendanceMap = new HashMap<>();
    for (Attendance a : records) {
        int empId = a.getEmployeeId();
        int day = a.getAttendanceDate().toLocalDate().getDayOfMonth();
        String status = a.getStatus();

        attendanceMap
            .computeIfAbsent(empId, k -> new HashMap<>())
            .put(day, status);
    }

    // Add row for each employee
    YearMonth ym = YearMonth.of(year, month);
    int daysInMonth = ym.lengthOfMonth();

    for (Employee emp : employees) {
        Object[] row = new Object[2 + daysInMonth];
        row[0] = emp.getEmployeeId();
        row[1] = emp.getFullName();

        for (int d = 1; d <= daysInMonth; d++) {
            String status = attendanceMap
                .getOrDefault(emp.getEmployeeId(), Collections.emptyMap())
                .getOrDefault(d, "");
            row[d + 1] = status;
        }
        model.addRow(row);
    }
}

    
} 
