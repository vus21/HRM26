package view;

import javax.swing.*;

import model.entity.Position;

import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;

    public MainView() {
        setTitle("HR Management System - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees", new EmployeeView());
        tabbedPane.addTab("Department", new EmployeeView());
        tabbedPane.addTab("Position", new PositionView());
        tabbedPane.addTab("Attendance", new AttendanceView());
        tabbedPane.addTab("Payroll", new PayrollView());
        tabbedPane.addTab("Leave Requests", new LeaveRequestView());
        tabbedPane.addTab("Performance", new PerformanceView());
        tabbedPane.addTab("Reports", new ReportView());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}