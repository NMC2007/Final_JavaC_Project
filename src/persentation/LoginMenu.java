package persentation;

import business.impl.AdminLogin;
import business.impl.LoginService;
import business.impl.StudentLogin;
import model.Admin;
import model.Student;
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
    String username = InputValidator.inputString(sc, "Tên đăng nhập:\n");
    String password = InputValidator.inputString(sc, "Mật khẩu:\n");

    AdminLogin adminLogin = new AdminLogin();

    Admin admin = adminLogin.CheckLogin(username, password);

    if (admin != null) {
        System.out.println("\n\n\n===========================================");
        System.out.println("Đăng nhập thành công tài khoản");
        System.out.printf("""
                    Quyền Admin
                    ID: %d
                    Tên: %s
                    """, admin.getId(), admin.getUsername());
        LoginService.loginAdmin(sc, admin);
    } else  {
        System.out.println("❌ Tên đăng nhập hoặc mật khẩu không đúng.");
    }
}

    private static void loginStudent(Scanner sc) {
        String email = InputValidator.inputEmail(sc, "Email sinh viên:\n");
        String password = InputValidator.inputString(sc, "Mật khẩu:\n");

        StudentLogin studentLogin = new StudentLogin();

        Student student = studentLogin.CheckLogin(email, password);

        if (student != null) {

            System.out.println("\n\n\n===========================================");
            System.out.println("Đăng nhập thành công");

            System.out.printf("""
                    Quyền Sinh viên
                    ID: %d
                    Tên: %s
                    Email: %s
                    """, student.getId(), student.getName(), student.getEmail());
            LoginService.loginStudent(sc,  student);
        } else {
            System.out.println("❌ Email hoặc mật khẩu không đúng.");
        }
    }
}
