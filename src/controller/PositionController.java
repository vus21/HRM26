package controller;

import model.entity.Department;
import model.entity.Position;
import model.service.DepartmentService;
import model.service.PositionService;
import view.PositionView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

public class PositionController {
    private PositionView view;
    private PositionService positionService;
    private List<Department> departments;
    private DepartmentService departmentService;
     

    public PositionController(PositionView view) {
        this.view = view;
        this.positionService = new PositionService();
        this.departmentService = new DepartmentService();
        getAllDepartments();
        loadPositions();
        initListeners();
    }
    private void initListeners() {
        view.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Position position = createPositionFromView();
                if (position != null) {
                    positionService.addPosition(position);
                    loadPositions();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Position added!");
                }
            }
        });

        view.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.getTxtPositionId().getText());
                    Position updated = createPositionFromView();
                    if (updated != null) {
                        positionService.updatePosition(id, updated);
                        loadPositions();
                        clearFields();
                        JOptionPane.showMessageDialog(null, "Position updated!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID");
                }
            }
        });

        view.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.getTxtPositionId().getText());
                    int confirm = JOptionPane.showConfirmDialog(null, "Delete position ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        positionService.deletePosition(id);
                        loadPositions();
                        clearFields();
                        JOptionPane.showMessageDialog(null, "Deleted!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID");
                }
            }
        });

        view.getBtnClear().addActionListener(e -> clearFields());
        view.getBtnLoad().addActionListener(e -> {
            getAllDepartments();
            loadPositions();
            clearFields();
        });

        view.getTable().getSelectionModel().addListSelectionListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                view.getTxtPositionId().setText(view.getTableModel().getValueAt(row, 0).toString());
                view.getTxtPositionName().setText(view.getTableModel().getValueAt(row, 1).toString());
                view.getTxtCreatedAt().setText(view.getTableModel().getValueAt(row, 3).toString());

                // Set selected department in combo box
                String deptName = view.getTableModel().getValueAt(row, 2).toString();
                for (int i = 0; i < view.getComboDepartment().getItemCount(); i++) {
                    if (view.getComboDepartment().getItemAt(i).getDepartmentName().equals(deptName)) {
                        view.getComboDepartment().setSelectedIndex(i);
                        break;
                    }
                }
            }
        });
    }

    private Position createPositionFromView() {
        String name = view.getTxtPositionName().getText().trim();
        Department dept = (Department) view.getComboDepartment().getSelectedItem();

        if (name.isEmpty() || dept == null) {
            JOptionPane.showMessageDialog(null, "Please enter name and select department.");
            return null;
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new Position(0, name, dept.getDepartmentId(), createdAt); // ID = 0 => auto-increment
    }

    private void loadPositions() {

        List<Position> positions = positionService.getAllPositions();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        for (Position pos : positions) {
            Department dept = departmentService.getDepartmentById(pos.getDepartmentId());
            model.addRow(new Object[] {
                    pos.getPositionId(),
                    pos.getPositionName(),
                    dept != null ? dept.getDepartmentName() : "Unknown",
                    pos.getCreatedAt()
                    
            });
        }
    }

   public void loadDepartments(List<Department> departments) {
        view.getComboDepartment().removeAllItems();
        for (Department dept : departments) {
            view.getComboDepartment().addItem(dept);
        }
    }
public void getAllDepartments() {
        DepartmentService departmentService = new DepartmentService();
        departments = departmentService.getAllDepartments();
        loadDepartments(departments);
    }
    private void clearFields() {
        view.getTxtPositionId().setText("0");
        view.getTxtPositionName().setText("");
        view.getTxtCreatedAt().setText("");
        view.getComboDepartment().setSelectedIndex(-1);
        view.getTable().clearSelection();
    }
}
