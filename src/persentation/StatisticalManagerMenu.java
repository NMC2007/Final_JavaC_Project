package persentation;

import business.impl.StatisticalServiceImpl;
import validation.InputValidator;

import java.util.Scanner;

public class StatisticalManagerMenu {
    private static final StatisticalServiceImpl statisticalService = new StatisticalServiceImpl();
    public static void showMenu(Scanner sc) {
        while (true) {
            System.out.println("""
                    \n\n============================= MENU THỐNG KÊ =============================
                    1. Thống kê tổng số lượng khóa học và học viên
                    2. Thống kê học viên theo từng khóa học
                    3. Top 5 khóa học đông học viên nhất
                    4. Liệt kê khóa học có trên 10 học viên
                    5. Quay về menu chính
                    """);

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn của bạn: ", 5);

            switch (choice) {
                case 1:
                    TotalCoursesAndStudents();
                    break;
                case 2:
                    StudentCountByCourse();
                    break;
                case 3:
                    Top5CoursesByStudentCount();
                    break;
                case 4:
                    CoursesWithMoreThan10Students();
                    break;
                case 5:
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void TotalCoursesAndStudents() {
        statisticalService.getTotalCoursesAndStudents();
    }
    private static void StudentCountByCourse() {
        statisticalService.getStudentCountByCourse();
    }
    private static void Top5CoursesByStudentCount() {
        statisticalService.getTop5CoursesByStudentCount();
    }
    private static void CoursesWithMoreThan10Students() {
        statisticalService.getCoursesWithMoreThan10Students();
    }

}
