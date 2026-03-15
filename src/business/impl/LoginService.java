package business.impl;

import model.Admin;
import model.Student;
import persentation.AdminManagerMenu;
import persentation.StudentViewMenu;

import java.util.Scanner;

public class LoginService {
    public static void loginAdmin(Scanner sc, Admin admin) {
        int id = admin.getId();
        if (id > 0) {
            AdminManagerMenu.showMenu(sc, id);
        }
    }

    public static void loginStudent(Scanner sc,  Student student) {
        int id = student.getId();
        if (id > 0) {
            StudentViewMenu.showMenu(sc, id);
        }
    }
}
