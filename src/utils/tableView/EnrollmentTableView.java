package utils.tableView;

import model.EnrollmentView;

import java.util.List;

public class EnrollmentTableView {
    public static void printListEnrollment(List<EnrollmentView> enrollmentList) {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-25s | %-35s | %-10s |\n", "ID Course", "Course Name", "Registered At", "Status");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (EnrollmentView enrollment : enrollmentList) {
            enrollment.display();
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }
}
