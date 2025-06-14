 
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ReportView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ReportView() {
        setLayout(new BorderLayout());

        // Tạo JTable
        String[] columnNames = {"Report Type", "Date", "Details"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm vào layout
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
}