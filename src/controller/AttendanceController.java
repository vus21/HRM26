package controller;

import model.entity.Attendance;
import model.entity.Employee;
import model.service.AttendanceService;
import model.service.EmployeeService;
import view.AttendanceView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class AttendanceController {
    private AttendanceView attendanceView;
    private AttendanceService attendanceService;
    private EmployeeService employeeService;

    public AttendanceController(AttendanceView view) {
        this.attendanceView = view;
        this.attendanceService = new AttendanceService();
        this.employeeService = new EmployeeService();

        loadEmployeesToComboBox();  // 🔹 Thêm dòng này
        loadAttendances();
        initListeners();
    }

    private void initListeners() {
        attendanceView.getBtnAdd().addActionListener(e -> addAttendance());
        attendanceView.getBtnUpdate().addActionListener(e -> updateAttendance());
        attendanceView.getBtnDelete().addActionListener(e -> deleteAttendance());
        attendanceView.getBtnClear().addActionListener(e -> clearFields());

        attendanceView.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = attendanceView.getTable().getSelectedRow();
                if (row >= 0) {
                    attendanceView.getTxtAttendanceId().setText(attendanceView.getTableModel().getValueAt(row, 0).toString());

                    int employeeId = Integer.parseInt(attendanceView.getTableModel().getValueAt(row, 1).toString());
                    selectEmployeeInCombo(employeeId);  // 🔹 Set lại combo box theo ID

                    attendanceView.getTxtAttendanceDate().setText(attendanceView.getTableModel().getValueAt(row, 2).toString());
                    attendanceView.getTxtCheckInTime().setText(attendanceView.getTableModel().getValueAt(row, 3).toString());
                    attendanceView.getTxtCheckOutTime().setText(attendanceView.getTableModel().getValueAt(row, 4).toString());
                    attendanceView.getComboStatus().setSelectedItem(attendanceView.getTableModel().getValueAt(row, 5).toString());
                    attendanceView.getTxtWorkHours().setText(attendanceView.getTableModel().getValueAt(row, 6).toString());
                    attendanceView.getTxtCreatedAt().setText(attendanceView.getTableModel().getValueAt(row, 7).toString());
                }
            }
        });
    }

    private Attendance createAttendanceFromView() {
        int id = Integer.parseInt(attendanceView.getTxtAttendanceId().getText());

        // 🔹 Lấy employee_id từ combo box
        String selectedItem = attendanceView.getComboEmployee().getSelectedItem().toString(); // "1 - John Doe"
        int empId = Integer.parseInt(selectedItem.split(" - ")[0].trim());

        Date date = Date.valueOf(attendanceView.getTxtAttendanceDate().getText());
        Time checkIn = Time.valueOf(attendanceView.getTxtCheckInTime().getText());
        Time checkOut = Time.valueOf(attendanceView.getTxtCheckOutTime().getText());
        String status = attendanceView.getComboStatus().getSelectedItem().toString();
        double hours = (checkOut.getTime() - checkIn.getTime()) / (1000.0 * 60 * 60);
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        return new Attendance(id, empId, date, checkIn, checkOut, status, hours, createdAt);
    }

    private void loadAttendances() {
        List<Attendance> list = attendanceService.getAllAttendance();
        DefaultTableModel model = attendanceView.getTableModel();
        model.setRowCount(0);
        for (Attendance a : list) {
            model.addRow(new Object[] {
                a.getAttendanceId(), a.getEmployeeId(), a.getAttendanceDate(), a.getCheckInTime(),
                a.getCheckOutTime(), a.getStatus(), a.getWorkHours(), a.getCreatedAt()
            });
        }
    }

    private void loadEmployeesToComboBox() {
        List<Employee> employees = employeeService.getAllEmployees();
        JComboBox<String> combo = attendanceView.getComboEmployee();
        combo.removeAllItems();
        for (Employee emp : employees) {
            combo.addItem(emp.getEmployeeId() + " - " + emp.getFirstName() + " " + emp.getLastName());
        }
    }

    private void selectEmployeeInCombo(int employeeId) {
        JComboBox<String> combo = attendanceView.getComboEmployee();
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item.startsWith(employeeId + " -")) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void clearFields() {
        attendanceView.getTxtAttendanceId().setText("0");
        attendanceView.getComboEmployee().setSelectedIndex(0);
        attendanceView.getTxtAttendanceDate().setText("");
        attendanceView.getTxtCheckInTime().setText("");
        attendanceView.getTxtCheckOutTime().setText("");
        attendanceView.getComboStatus().setSelectedIndex(0);
        attendanceView.getTxtWorkHours().setText("");
        attendanceView.getTxtCreatedAt().setText("");
    }

    private void addAttendance() {
        try {
            Attendance a = createAttendanceFromView();
            attendanceService.addAttendance(a);
            loadAttendances();
            clearFields();
            JOptionPane.showMessageDialog(null, "Added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Add failed: " + ex.getMessage());
        }
    }

    private void updateAttendance() {
        try {
            int id = Integer.parseInt(attendanceView.getTxtAttendanceId().getText());
            Attendance a = createAttendanceFromView();
            attendanceService.updateAttendance(id, a);
            loadAttendances();
            clearFields();
            JOptionPane.showMessageDialog(null, "Updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Update failed: " + ex.getMessage());
        }
    }

    private void deleteAttendance() {
        try {
            int id = Integer.parseInt(attendanceView.getTxtAttendanceId().getText());
            attendanceService.deleteAttendance(id);
            loadAttendances();
            clearFields();
            JOptionPane.showMessageDialog(null, "Deleted successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Delete failed: " + ex.getMessage());
        }
    }
}
