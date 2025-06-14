 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PerformanceView extends JPanel {
    private JTextField txtEvaluationId, txtEmployeeId, txtEvaluationDate, txtScore, txtComments;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public PerformanceView() {
        setLayout(new BorderLayout());

        // Tạo panel form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(new JLabel("Evaluation ID:"));
        txtEvaluationId = new JTextField(15);
        formPanel.add(txtEvaluationId);
        formPanel.add(new JLabel("Employee ID:"));
        txtEmployeeId = new JTextField(15);
        formPanel.add(txtEmployeeId);
        formPanel.add(new JLabel("Evaluation Date:"));
        txtEvaluationDate = new JTextField(15);
        formPanel.add(txtEvaluationDate);
        formPanel.add(new JLabel("Score:"));
        txtScore = new JTextField(15);
        formPanel.add(txtScore);
        formPanel.add(new JLabel("Comments:"));
        txtComments = new JTextField(15);
        formPanel.add(txtComments);

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
        String[] columnNames = {"ID", "Employee ID", "Date", "Score", "Comments", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtEvaluationId() { return txtEvaluationId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtEvaluationDate() { return txtEvaluationDate; }
    public JTextField getTxtScore() { return txtScore; }
    public JTextField getTxtComments() { return txtComments; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}