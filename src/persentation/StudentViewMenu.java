package persentation;

import business.impl.StudentViewServiceImpl;
import validation.InputValidator;

import java.util.Scanner;

public class StudentViewMenu {
    private static final StudentViewServiceImpl studentViewService = new StudentViewServiceImpl();
    public static void showMenu(Scanner sc, int idStudent) {
        while (true) {
            System.out.println("\n\n======== MENU SINH VIÊN ========");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("================================");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 6);

            switch (choice) {
                case 1:
                    showListCourse();
                    break;
                case 2:
                    createEnrollment(sc, idStudent);
                    break;
                case 3:
                    showEnrollment(idStudent);
                    break;
                case 4:
                    deleteEnrollment(sc, idStudent);
                    break;
                case 5:
                    updatePassword(sc, idStudent);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void showListCourse() {
        studentViewService.showCourse();
    }

    private static void createEnrollment(Scanner sc, int idStudent) {
        studentViewService.createEnrollment(sc, idStudent);
    }

    private static void showEnrollment(int idStudent) {
        studentViewService.showEnrollment(idStudent);
    }

    private static void deleteEnrollment(Scanner sc, int idStudent) {
        studentViewService.deleteEnrollment(sc, idStudent);
    }

    private static void updatePassword(Scanner sc, int idStudent) {
        studentViewService.updatePassword(sc, idStudent);
    }
}
