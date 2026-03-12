package persentation;

import validation.InputValidator;

import java.util.Scanner;

public class StatisticalManagerMenu {
    public static void showMenu(Scanner sc) {
        while (true) {
            System.out.println("\n===== MENU THỐNG KÊ =====");
            System.out.println("1. Thống kê tổng số lượng khóa học và học viên");
            System.out.println("2. Thống kê học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê khóa học có trên 10 học viên");
            System.out.println("5. Quay về menu chính");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 5);

            switch (choice) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
                case 5:

                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
}
