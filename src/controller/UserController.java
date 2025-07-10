package controller;

import model.entity.User;
import model.entity.Employee;
import model.service.UserService;
import model.service.EmployeeService;
import view.UserView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserController {
    private UserView userView;
    private UserService userService;
    private EmployeeService employeeService;

    public UserController(UserView view) {
        this.userView = view;
        this.userService = new UserService();
        this.employeeService = new EmployeeService();
        LoadRole();
        init();
    }
    private void LoadRole() {
        JComboBox<String> comboRole = userView.getComboRole();
        comboRole.removeAllItems();
        List<String> roles = userService.getAllRoles();
        for (String role : roles) {
            comboRole.addItem(role);
        }
    }
    private void init() {
        loadEmployeeIds();
        loadUsers();

        userView.getBtnAdd().addActionListener(e -> addUser());
        userView.getBtnUpdate().addActionListener(e -> updateUser());
        userView.getBtnDelete().addActionListener(e -> deleteUser());
        userView.getBtnClear().addActionListener(e -> clearForm());

        userView.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromTable();
            }
        });
    }

    private void loadEmployeeIds() {
        List<Employee> employees = employeeService.getAllEmployees();
        JComboBox<String> combo = userView.getComboEmployeeId();
        combo.removeAllItems();
        for (Employee emp : employees) {
            combo.addItem(emp.getFullName());
        }
    }

    private void loadUsers() {
        DefaultTableModel model = userView.getTableModel();
        model.setRowCount(0);
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            model.addRow(new Object[]{
                u.getUserId(),
                u.getEmployeeId(),
                u.getUsername(),
                u.getRole(),
                u.getCreatedAt().toString()
            });
        }
    }

    private void addUser() {
        try {
            int empId = (int) userView.getComboEmployeeId().getSelectedItem();
            String username = userView.getTxtUsername().getText();
            String password = userView.getTxtPassword().getText();
            String role = (String) userView.getComboRole().getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(userView, "Username and Password cannot be empty");
                return;
            }

            User user = new User();
            user.setEmployeeId(empId);
            user.setUsername(username);
            user.setPasswordHash(password); // TODO: hash trước khi lưu
            user.setRole(role);

            userService.insertUser(user);
            JOptionPane.showMessageDialog(userView, "User added successfully!");

            loadUsers();
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userView, "Error adding user: " + ex.getMessage());
        }
    }

    private void updateUser() {
        try {
            int userId = Integer.parseInt(userView.getTxtUserId().getText());
            int empId = (int) userView.getComboEmployeeId().getSelectedItem();
            String username = userView.getTxtUsername().getText();
            String password = userView.getTxtPassword().getText();
            String role = (String) userView.getComboRole().getSelectedItem();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(userView, "Username cannot be empty");
                return;
            }

            User user = new User();
            user.setUserId(userId);
            user.setEmployeeId(empId);
            user.setUsername(username);
            if (!password.isEmpty()) {
                user.setPasswordHash(password); // TODO: hash trước khi update
            }
            user.setRole(role);

            userService.updateUser(user);
            JOptionPane.showMessageDialog(userView, "User updated successfully!");

            loadUsers();
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userView, "Error updating user: " + ex.getMessage());
        }
    }

    private void deleteUser() {
        try {
            int userId = Integer.parseInt(userView.getTxtUserId().getText());
            userService.deleteUser(userId);
            JOptionPane.showMessageDialog(userView, "User deleted successfully!");

            loadUsers();
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userView, "Error deleting user: " + ex.getMessage());
        }
    }

    private void clearForm() {
        userView.getTxtUserId().setText("0");
        userView.getComboEmployeeId().setSelectedIndex(0);
        userView.getTxtUsername().setText("");
        userView.getTxtPassword().setText("");
        userView.getComboRole().setSelectedIndex(0);
        userView.getTxtCreatedAt().setText("");
    }

    private void fillFormFromTable() {
        int row = userView.getTable().getSelectedRow();
        if (row >= 0) {
            userView.getTxtUserId().setText(userView.getTableModel().getValueAt(row, 0).toString());
            userView.getComboEmployeeId().setSelectedItem(Integer.parseInt(userView.getTableModel().getValueAt(row, 1).toString()));
            userView.getTxtUsername().setText(userView.getTableModel().getValueAt(row, 2).toString());
            userView.getComboRole().setSelectedItem(userView.getTableModel().getValueAt(row, 3).toString());
            userView.getTxtCreatedAt().setText(userView.getTableModel().getValueAt(row, 4).toString());
        }
    }
}
