import view.MainView;
import controller.EmployeeController;
import view.EmployeeView;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tạo MainView
            MainView mainView = new MainView();

            // Tạo EmployeeView riêng và gắn vào tab "Employees"
            EmployeeView employeeView = new EmployeeView();
            new EmployeeController(employeeView);
            mainView.getTabbedPane().setComponentAt(0, employeeView); // Thay tab "Employees" bằng EmployeeView

            // Hiển thị giao diện
            mainView.setVisible(true);
        });
    }
}