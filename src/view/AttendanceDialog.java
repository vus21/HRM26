package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AttendanceDialog extends JDialog {
    private JTextField txtCheckIn, txtCheckOut;
    private JComboBox<String> comboStatus;
    private JButton btnSave, btnDelete;

    public AttendanceDialog(JFrame parent, int empId, String empName, int day, int month, int year) {
        super(parent, "Attendance - " + empName + " - " + day + "/" + (month + 1) + "/" + year, true);
        setLayout(new BorderLayout(10, 10));

        // Info label
        JLabel lblInfo = new JLabel("Employee: " + empName + " | Date: " + String.format("%02d/%02d/%d", day, month + 1, year));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblInfo, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("Check In (HH:mm:ss):"));
        txtCheckIn = new JTextField("08:00:00");
        formPanel.add(txtCheckIn);

        formPanel.add(new JLabel("Check Out (HH:mm:ss):"));
        txtCheckOut = new JTextField("17:00:00");
        formPanel.add(txtCheckOut);

        formPanel.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{"OnTime", "Late", "EarlyLeave", "Absent"});
        formPanel.add(comboStatus);

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 250); // Tăng kích thước
        setLocationRelativeTo(parent);
    }

    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnDelete() { return btnDelete; }
    public JTextField getTxtCheckIn() { return txtCheckIn; }
    public JTextField getTxtCheckOut() { return txtCheckOut; }
    public JComboBox<String> getComboStatus() { return comboStatus; }
}
