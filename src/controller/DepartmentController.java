 
package controller;

import model.entity.Department;
import model.service.DepartmentService;
import view.DepartmentView;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.List;
import controller.PositionController;
import controller.EmployeeController;

public class DepartmentController {
    private DepartmentView departmentView;
    private DepartmentService departmentService;

    public DepartmentController(DepartmentView view) {
        this.departmentView = view;
        this.departmentService = new DepartmentService();
        initListeners();
        loadDepartments();
    }

    private void initListeners() {
        departmentView.getBtnAdd().addActionListener(e -> {
            try {
                Department dept = createDepartmentFromView();
                departmentService.addDepartment(dept);
                loadDepartments();
                clearFields();
                JOptionPane.showMessageDialog(null, "Department added successfully!");
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        departmentView.getBtnUpdate().addActionListener(e -> {
            try {
                int id = Integer.parseInt(departmentView.getTxtDepartmentId().getText());
                int confirm = JOptionPane.showConfirmDialog(null, "Update department ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Department dept = createDepartmentFromView();
                    departmentService.updateDepartment(id, dept);
                    loadDepartments();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Department updated successfully!");
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        departmentView.getBtnDelete().addActionListener(e -> {
            try {
                int id = Integer.parseInt(departmentView.getTxtDepartmentId().getText());
                int confirm = JOptionPane.showConfirmDialog(null, "Delete department ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    departmentService.deleteDepartment(id);
                    loadDepartments();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Department deleted successfully!");
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        departmentView.getBtnClear().addActionListener(e -> clearFields());

        departmentView.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = departmentView.getTable().getSelectedRow();
                if (row >= 0) {
                    departmentView.getTxtDepartmentId().setText(departmentView.getTableModel().getValueAt(row, 0).toString());
                    departmentView.getTxtDepartmentName().setText(departmentView.getTableModel().getValueAt(row, 1).toString());
                    departmentView.getTxtCreatedAt().setText(departmentView.getTableModel().getValueAt(row, 2).toString());
                }
            }
        });
    }

    private Department createDepartmentFromView() {
        int id = Integer.parseInt(departmentView.getTxtDepartmentId().getText());
        String name = departmentView.getTxtDepartmentName().getText().trim();
        String createdAtStr = departmentView.getTxtCreatedAt().getText().trim();

        if (name.isEmpty()) {
            throw new RuntimeException("Department name cannot be empty.");
        }

        Timestamp createdAt;
        try {
            createdAt = Timestamp.valueOf(createdAtStr);
        } catch (IllegalArgumentException e) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }

        return new Department(id, name, createdAt);
    }

    private void loadDepartments() {
        List<Department> list = departmentService.getAllDepartments();
        var model = departmentView.getTableModel();
        model.setRowCount(0);

        for (Department dept : list) {
            model.addRow(new Object[]{
                dept.getDepartmentId(),
                dept.getDepartmentName(),
                dept.getCreatedAt()
            });
        }
        // load cho cac page khác
        
    }

    private void clearFields() {
        departmentView.getTxtDepartmentId().setText("0");
        departmentView.getTxtDepartmentName().setText("");
        departmentView.getTxtCreatedAt().setText("");
    }
}
