 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DepartmentView extends JPanel {
    private JTextField txtDepartmentId, txtDepartmentName, txtCreatedAt;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnCancel;

    public DepartmentView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Department Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Department Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Department ID:"), gbc);
        gbc.gridx = 1;
        txtDepartmentId = new JTextField(15);
        txtDepartmentId.setText("0"); // default
        txtDepartmentId.setEditable(false); // ID should not be editable
        formPanel.add(txtDepartmentId, gbc);

        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Department Name:"), gbc);
        gbc.gridx = 1;
        txtDepartmentName = new JTextField(15);
        formPanel.add(txtDepartmentName, gbc);

        // Row 2: Created At
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Created At:"), gbc);
        gbc.gridx = 1;
        txtCreatedAt = new JTextField(15);
        txtCreatedAt.setEditable(false); // Created At should not be editable
        txtCreatedAt.setText(java.time.LocalDateTime.now().toString()); // default to
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
        String[] columnNames = {"ID", "Department Name", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(24);
        table.setGridColor(Color.LIGHT_GRAY);
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
    public JTextField getTxtDepartmentId() { return txtDepartmentId; }
    public JTextField getTxtDepartmentName() { return txtDepartmentName; }
    public JTextField getTxtCreatedAt() { return txtCreatedAt; }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnCancel() { return btnCancel; }
}
