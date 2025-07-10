package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserView extends JPanel {
    private JTextField txtUserId, txtUsername, txtPassword, txtCreatedAt;
    private JComboBox<String> comboEmployeeId;
    private JComboBox<String> comboRole;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public UserView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("User Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("User Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // user_id
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        txtUserId = new JTextField("0", 15);
        txtUserId.setEditable(false);
        formPanel.add(txtUserId, gbc);

        // employee_id
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        comboEmployeeId = new JComboBox<>();
        comboEmployeeId.setPreferredSize(new Dimension(200, 25));
        formPanel.add(comboEmployeeId, gbc);

        // username
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        formPanel.add(txtUsername, gbc);

        // password
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JTextField(15);
        formPanel.add(txtPassword, gbc);

        // role
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        comboRole = new JComboBox<>();
        formPanel.add(comboRole, gbc);

        // created_at
        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Created At:"), gbc);
        gbc.gridx = 1;
        txtCreatedAt = new JTextField(15);
        txtCreatedAt.setEditable(false);
        formPanel.add(txtCreatedAt, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = createButton("Add");
        btnUpdate = createButton("Update");
        btnDelete = createButton("Delete");
        btnClear = createButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table
        String[] columnNames = {"ID", "Employee ID", "Username", "Role", "Created At"};
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
    public JTextField getTxtUserId() { return txtUserId; }
    public JComboBox<String> getComboEmployeeId() { return comboEmployeeId; }
    public JTextField getTxtUsername() { return txtUsername; }
    public JTextField getTxtPassword() { return txtPassword; }
    public JComboBox<String> getComboRole() { return comboRole; }
    public JTextField getTxtCreatedAt() { return txtCreatedAt; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}
