 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PayrollView extends JPanel {
    private JTextField txtPayrollId, txtEmployeeId, txtPayPeriod, txtBaseSalary, txtAllowance,
                       txtBonus, txtTaxDeduction, txtNetSalary, txtNotes;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public PayrollView() {
        setLayout(new BorderLayout());

        // Tạo panel form
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.add(new JLabel("Payroll ID:"));
        txtPayrollId = new JTextField(15);
        formPanel.add(txtPayrollId);
        formPanel.add(new JLabel("Employee ID:"));
        txtEmployeeId = new JTextField(15);
        formPanel.add(txtEmployeeId);
        formPanel.add(new JLabel("Pay Period:"));
        txtPayPeriod = new JTextField(15);
        formPanel.add(txtPayPeriod);
        formPanel.add(new JLabel("Base Salary:"));
        txtBaseSalary = new JTextField(15);
        formPanel.add(txtBaseSalary);
        formPanel.add(new JLabel("Allowance:"));
        txtAllowance = new JTextField(15);
        formPanel.add(txtAllowance);
        formPanel.add(new JLabel("Bonus:"));
        txtBonus = new JTextField(15);
        formPanel.add(txtBonus);
        formPanel.add(new JLabel("Tax Deduction:"));
        txtTaxDeduction = new JTextField(15);
        formPanel.add(txtTaxDeduction);
        formPanel.add(new JLabel("Net Salary:"));
        txtNetSalary = new JTextField(15);
        formPanel.add(txtNetSalary);
        formPanel.add(new JLabel("Notes:"));
        txtNotes = new JTextField(15);
        formPanel.add(txtNotes);

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
        String[] columnNames = {"ID", "Employee ID", "Pay Period", "Base Salary", "Allowance", "Bonus", "Tax", "Net Salary", "Notes", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtPayrollId() { return txtPayrollId; }
    public JTextField getTxtEmployeeId() { return txtEmployeeId; }
    public JTextField getTxtPayPeriod() { return txtPayPeriod; }
    public JTextField getTxtBaseSalary() { return txtBaseSalary; }
    public JTextField getTxtAllowance() { return txtAllowance; }
    public JTextField getTxtBonus() { return txtBonus; }
    public JTextField getTxtTaxDeduction() { return txtTaxDeduction; }
    public JTextField getTxtNetSalary() { return txtNetSalary; }
    public JTextField getTxtNotes() { return txtNotes; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}