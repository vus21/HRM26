package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Vector;

public class AttendanceView extends JPanel {
    private JComboBox<Integer> comboYear;
    private JComboBox<Integer> comboMonth;
    private JTable table;
    private DefaultTableModel tableModel;
    private TableColumnModel colModel;

    public AttendanceView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel for Year and Month selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboYear = new JComboBox<>();
        for (int y = 2020; y <= 2030; y++) {
            comboYear.addItem(y);
        }
        comboMonth = new JComboBox<>();
        for (int m = 1; m <= 12; m++) {
            comboMonth.addItem(m);
        }
        topPanel.add(new JLabel("Year:"));
        topPanel.add(comboYear);
        topPanel.add(new JLabel("Month:"));
        topPanel.add(comboMonth);

        add(topPanel, BorderLayout.NORTH);

        // Initialize table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing cells from day columns (2 onwards)
                return column >= 2;
            }
        };
        table.setRowHeight(24);
        //set do rong
      table.setRowHeight(24);
       
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize headers with default month (e.g., January 2025)
        setTableHeaders(2025, 1);
    }
    // Trong file AttendanceView.java


    public void setTableHeaders(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        int daysInMonth = ym.lengthOfMonth();

        Vector<String> columns = new Vector<>();
        columns.add("ID");
        columns.add("Name");
        
        for (int i = 1; i <= daysInMonth; i++) {
            columns.add(String.valueOf(i));
        }

        tableModel.setColumnIdentifiers(columns);
         colModel = table.getColumnModel();
colModel.getColumn(1).setPreferredWidth(300);
 // Gọi hàm để thiết lập màu chủ nhật
table.setDefaultRenderer(Object.class, new AttendanceCellRenderer());
     
        
    }

 public class AttendanceCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setForeground(Color.BLACK);
        c.setBackground(Color.WHITE);

        // Lấy thông tin năm và tháng hiện tại
        JComboBox<Integer> comboYear = ((AttendanceView) table.getParent().getParent().getParent()).getComboYear();
        JComboBox<Integer> comboMonth = ((AttendanceView) table.getParent().getParent().getParent()).getComboMonth();

        if (comboYear != null && comboMonth != null && column >= 2) {
            int year = (int) comboYear.getSelectedItem();
            int month = (int) comboMonth.getSelectedItem();
            int day = column - 1;

            LocalDate date = LocalDate.of(year, month, day);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                c.setBackground(new Color(241, 195, 207)); // Chủ nhật
            }

            String status = String.valueOf(value);
            if ("Absent".equalsIgnoreCase(status)) {
                setText("O");
            } else if ("OnTime".equalsIgnoreCase(status) || 
                       "Late".equalsIgnoreCase(status) || 
                       "EarlyLeave".equalsIgnoreCase(status)) {
                setText("X");
            } else {
                setText(""); // chưa có dữ liệu
            }
        } else {
            setText(String.valueOf(value)); // cột ID, Name giữ nguyên
        }

        return c;
    }
}

    public JComboBox<Integer> getComboYear() {
        return comboYear;
    }

    public JComboBox<Integer> getComboMonth() {
        return comboMonth;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public TableColumnModel getColModel() {
        return colModel;
    }
    
}
