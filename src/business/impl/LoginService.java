package business.impl;

import persentation.AdminManagerMenu;
import persentation.StudentViewMenu;

import java.util.Scanner;

public class LoginService {
    public static void loginAdmin(Scanner sc) {
        AdminLogin adminLogin = new AdminLogin();
        int id = adminLogin.CheckLogin(sc);
        if (id > 0) {
            AdminManagerMenu.showMenu(sc, id);
        }
    }

    public static void loginStudent(Scanner sc) {
        StudentLogin studentLogin = new StudentLogin();
        int id = studentLogin.CheckLogin(sc);
        if (id > 0) {
            StudentViewMenu.showMenu(sc, id);
        }
    }
}
