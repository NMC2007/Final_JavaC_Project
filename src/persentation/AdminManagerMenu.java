package persentation;

import validation.InputValidator;

import java.util.Scanner;

public class AdminManagerMenu {
    public static void showMenu(Scanner sc, int id) {

        while (true) {

            System.out.println("\n=========== MENU ADMIN ===========");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký khóa học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Đăng xuất");
            System.out.println("==================================");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 5);

            switch (choice) {

                case 1:
                    // mở menu quản lý khóa học
                    CourseManagerMenu.showMenu(sc);
                    break;

                case 2:
                    // mở menu quản lý học viên
                    StudentManagerMenu.showMenu(sc);
                    break;

                case 3:
                    // menu quản lý đăng ký khóa học
                    // EnrollmentManagerMenu.showMenu(sc);
                    break;

                case 4:
                    // thống kê học viên theo khóa học
                    // StatisticsMenu.showMenu(sc);
                    break;

                case 5:
                    System.out.println("Đăng xuất thành công!");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
