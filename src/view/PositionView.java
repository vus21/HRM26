package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.entity.Department;
import java.awt.*;

public class PositionView extends JPanel {
    private JTextField txtPositionId, txtPositionName, txtCreatedAt;
    private JComboBox<Department> comboDepartment;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnLoad,  btnCancel;

    public PositionView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245)); // Màu nền nhẹ

        // Tiêu đề
        JLabel titleLabel = new JLabel("Position Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel với GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Position Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Thêm các trường vào form
        String[] labels = {"Position ID:", "Position Name:", "Department:", "Created At:", "Updated At:"};
        int row = 0;

        // Position ID
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(createLabel(labels[row]), gbc);
        txtPositionId = createTextField(false);
        txtPositionId.setText("0");
        gbc.gridx = 1; gbc.gridy = row++;
        formPanel.add(txtPositionId, gbc);

        // Position Name
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(createLabel(labels[row]), gbc);
        txtPositionName = createTextField(true);
        gbc.gridx = 1; gbc.gridy = row++;
        formPanel.add(txtPositionName, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(createLabel(labels[row]), gbc);
        comboDepartment = new JComboBox<>();
        comboDepartment.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1; gbc.gridy = row++;
        formPanel.add(comboDepartment, gbc);

        // Created At
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(createLabel(labels[row]), gbc);
        txtCreatedAt = createTextField(false);
        gbc.gridx = 1; gbc.gridy = row++;
        formPanel.add(txtCreatedAt, gbc);

        // Updated At
      
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        btnAdd = createButton("Add", "add.png");
        btnUpdate = createButton("Update", "update.png");
        btnDelete = createButton("Delete", "delete.png");
        btnClear = createButton("Clear", "clear.png");
        btnLoad = createButton("Load", "load.png");
        btnCancel = createButton("Cancel", "cancel.png");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnCancel);

        // JTable
        String[] columnNames = {"ID", "Position Name", "Department", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        // Thêm vào layout
        add(new JScrollPane(formPanel), BorderLayout.WEST);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private JTextField createTextField(boolean editable) {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setEditable(editable);
        textField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        return textField;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(100, 35));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        // Uncomment and add icon if available
        // try {
        //     button.setIcon(new ImageIcon(getClass().getResource("/icons/" + iconPath)));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        return button;
    }

    // Getter methods
    public JTextField getTxtPositionId() { return txtPositionId; }
    public JTextField getTxtPositionName() { return txtPositionName; }
    public JTextField getTxtCreatedAt() { return txtCreatedAt; }
    
    public JComboBox<Department> getComboDepartment() { return comboDepartment; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnLoad() { return btnLoad; }
    public JButton getBtnCancel() { return btnCancel; }
}