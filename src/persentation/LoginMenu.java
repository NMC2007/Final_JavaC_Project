package persentation;

import business.impl.AdminLogin;
import business.impl.StudentLogin;
import validation.InputValidator;

import java.util.Scanner;

public class LoginMenu {
    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== LOGIN MENU =====");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Đăng nhập Student");
            System.out.println("3. Thoát");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 3);

            switch (choice) {

                case 1:
                    loginAdmin(sc);
                    break;

                case 2:
                    loginStudent(sc);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }

    private static void loginAdmin(Scanner sc) {
        AdminLogin adminLogin = new AdminLogin();
        int id = adminLogin.CheckLogin(sc);
        if (id > 0) {
            AdminManagerMenu.showMenu(sc, id);
        }
    }

    private static void loginStudent(Scanner sc) {
        StudentLogin studentLogin = new StudentLogin();
        int id = studentLogin.CheckLogin(sc);
        if (id > 0) {
            StudentViewMenu.showMenu(sc, id);
        }
    }
}
