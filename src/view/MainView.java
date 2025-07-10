package view;

import javax.swing.*;



import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;

    public MainView() {
        setTitle("HR Management System - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
setExtendedState(JFrame.MAXIMIZED_BOTH);
setUndecorated(false); // Giữ viền và thanh tiêu đề (nếu muốn ẩn thì set true)
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees", new EmployeeView()); //0
        tabbedPane.addTab("Department", new EmployeeView()); //1
        tabbedPane.addTab("Position", new PositionView());//2
        tabbedPane.addTab("Attendance", new AttendanceView());  //3
        tabbedPane.addTab("Payroll", new PayrollView());    //4
        tabbedPane.addTab("Leave Requests", new LeaveRequestView()); //5
        // tabbedPane.addTab("Performance", new PerformanceView());
        tabbedPane.addTab("Reports", new ReportView());     //6
        tabbedPane.addTab("Users", new UserView());     //7


        add(tabbedPane, BorderLayout.CENTER);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}