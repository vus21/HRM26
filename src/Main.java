import view.MainView;
import controller.EmployeeController;
import controller.LeaveRequestController;
import controller.AttendanceController;
import controller.DepartmentController;
import controller.PositionController;
import model.entity.User;
import view.EmployeeView;
import view.LeaveRequestView;
import view.AttendanceDialog;
import view.AttendanceView;
import view.DepartmentView;
import view.PositionView;
import view.UserView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tạo MainView
            MainView mainView = new MainView();

            // Tab 0: Employee
            EmployeeView employeeView = new EmployeeView();
            new EmployeeController(employeeView);
            mainView.getTabbedPane().setComponentAt(0, employeeView);

            // Tab 1: Department
            DepartmentView departmentView = new DepartmentView();
            new DepartmentController(departmentView);
            mainView.getTabbedPane().setComponentAt(1, departmentView);

            // Tab 2: Position
            PositionView positionView = new PositionView();
            new PositionController(positionView);
            mainView.getTabbedPane().setComponentAt(2, positionView);
            
            AttendanceView attendanceView = new AttendanceView();
            new AttendanceController(attendanceView);
            mainView.getTabbedPane().setComponentAt(3, attendanceView);

            // Bạn có thể thêm các tab còn lại như sau khi có View/Controller:
            // mainView.getTabbedPane().setComponentAt(3, new AttendanceView());
            // mainView.getTabbedPane().setComponentAt(4, new PayrollView());
            LeaveRequestView leaveRequestView = new LeaveRequestView();
            new LeaveRequestController(leaveRequestView);
            mainView.getTabbedPane().setComponentAt(5, leaveRequestView);

            // mainView.getTabbedPane().setComponentAt(6, new PerformanceView());
            // mainView.getTabbedPane().setComponentAt(7, new ReportView());
            UserView userView = new UserView();
            new controller.UserController(userView);
            mainView.getTabbedPane().setComponentAt(7, userView);
            setview(mainView);
            // mainView.setVisible(true);
        });
    }
    private static void setview(JFrame frame) {
        
        frame.setVisible(true);}
}
