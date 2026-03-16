package persentation;

import business.impl.EnrollmentServiceImpl;
import validation.InputValidator;

import java.util.Scanner;

public class EnrollmentManagerMenu {
    private static final EnrollmentServiceImpl enrollmentService = new EnrollmentServiceImpl();

    public static void showMenu(Scanner sc) {
        while (true) {
            System.out.println("""
                    \n\n============================= MENU QUẢN LÝ ĐĂNG KÝ KHOÁ HỌC =============================
                    1. Hiển thị và sét duyệt sinh viên
                    2. Thêm sinh viên vào khóa học
                    3. Xóa sinh viên khỏi khóa học
                    4. Quay về menu chính
                    """);

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn của bạn: ", 4);

            switch (choice) {
                case 1:
                    showListStudentsBelongToCourse(sc);
                    break;
                case 2:
                    addStudentToTheCourse(sc);
                    break;
                case 3:
                    deleteStudentFromTheCourse(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }
    }

    private static void showListStudentsBelongToCourse(Scanner sc) {
        enrollmentService.showListStudentsBelongToCourse(sc);
    }
    private static void addStudentToTheCourse(Scanner sc) {
        enrollmentService.addStudentToTheCourse(sc);
    }
    private static void deleteStudentFromTheCourse(Scanner sc) {
        enrollmentService.deleteStudentFromTheCourse(sc);
    }
}
