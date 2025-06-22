 
package controller;

import model.entity.LeaveRequest;
import model.service.EmployeeService;
import model.service.LeaveRequestService;
import view.LeaveRequestView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class LeaveRequestController {
    private LeaveRequestView view;
    private LeaveRequestService service;
    private EmployeeService employeeService;

    public LeaveRequestController(LeaveRequestView view) {
        this.view = view;
        this.service = new LeaveRequestService();
        this.employeeService = new EmployeeService();
        loadLeaveRequests();
        initListeners();
    }

    private void initListeners() {
        view.getBtnApprove().addActionListener(e -> updateStatus("Approved"));
        view.getBtnReject().addActionListener(e -> updateStatus("Rejected"));
        view.getBtnClear().addActionListener(e -> clearFields());
        view.getBtnDelete().addActionListener(e -> {
            try {
                int leaveId = Integer.parseInt(view.getTxtLeaveId().getText());
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Confirm to delete leave request ?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    service.deleteLeaveRequest(leaveId);
                    JOptionPane.showMessageDialog(null, "Leave request deleted successfully!");
                    loadLeaveRequests();
                    clearFields();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to delete leave request:\n" + ex.getMessage());
            }
        });
        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    DefaultTableModel model = view.getTableModel();
                    view.getTxtLeaveId().setText(model.getValueAt(row, 0).toString());
                    view.getTxtEmployeeId().setText(model.getValueAt(row, 1).toString());
                    view.getTxtEmployeeName().setText(employeeService.getNameByID(Integer.parseInt(view.getTxtEmployeeId().getText())));
                    view.getTxtStartDate().setText(model.getValueAt(row, 2).toString());
                    view.getTxtEndDate().setText(model.getValueAt(row, 3).toString());
                    view.getComboLeaveType().setSelectedItem(model.getValueAt(row, 4).toString());
                    view.getComboStatus().setSelectedItem(model.getValueAt(row, 5).toString());
                    view.getTxtCreatedAt().setText(model.getValueAt(row, 7).toString());
                    if ("Pending".equals(model.getValueAt(row, 5).toString())) {
                        view.getBtnApprove().setEnabled(true);
                        view.getBtnReject().setEnabled(true);
                    } else {
                        view.getBtnApprove().setEnabled(false);
                        view.getBtnReject().setEnabled(false);
                    }
                }
            }
        });
    }

    private void updateStatus(String newStatus) {
        try {
            int leaveId = Integer.parseInt(view.getTxtLeaveId().getText());
            String currentStatus = view.getComboStatus().getSelectedItem().toString();

            if (!"Pending".equals(currentStatus)) {
                JOptionPane.showMessageDialog(null, "Only pending requests can be updated.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Confirm to " + newStatus.toLowerCase() + " leave request ID " + leaveId + "?",
                    "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;
           
          
            service.updateLeaveStatus(leaveId, newStatus);
            JOptionPane.showMessageDialog(null, "Leave request " + newStatus.toLowerCase() + "!");
            loadLeaveRequests();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to update status:\n" + ex.getMessage());
        }
    }

    private void loadLeaveRequests() {
        List<LeaveRequest> list = service.getAllLeaveRequests();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        for (LeaveRequest lr : list) {
            model.addRow(new Object[]{
                lr.getLeaveId(), lr.getEmployeeId(), lr.getStartDate(), lr.getEndDate(),
                lr.getLeaveType(), lr.getStatus(), lr.getRemainingLeaveDays(), lr.getCreatedAt()
            });
        }
    }

    private void clearFields() {
        view.getTxtLeaveId().setText("0");
        view.getTxtEmployeeId().setText("");
        view.getTxtEmployeeName().setText("");
        view.getTxtStartDate().setText("");
        view.getTxtEndDate().setText("");
        view.getComboLeaveType().setSelectedIndex(0);
        view.getComboStatus().setSelectedIndex(0);
        view.getTxtCreatedAt().setText("");
    }
}
