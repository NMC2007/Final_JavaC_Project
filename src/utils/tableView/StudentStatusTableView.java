package utils.tableView;

import model.StudentStatus;

import java.util.List;

public class StudentStatusTableView {
    public static void printListStudentStatus(List<StudentStatus> studentStatusList) {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-25s | %-5s | %-12s | %-10s |\n",
                "ID", "Name", "Email", "Sex", "Registered", "Status");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (StudentStatus studentStatus : studentStatusList) {
            studentStatus.display();
        }
        System.out.println("------------------------------------------------------------------------------------------------");
    }

    public static void printStudentStatus(StudentStatus studentStatusList) {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-25s | %-5s | %-12s | %-10s |\n",
                "ID", "Name", "Email", "Sex", "Registered", "Status");
        System.out.println("------------------------------------------------------------------------------------------------");
        studentStatusList.display();
        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
