package business;

import business.impl.ILoginBusiness;
import dao.AdminDAOImpl;
import model.Admin;
import validation.InputValidator;

import java.util.Scanner;

public class AdminLogin implements ILoginBusiness {
    public boolean CheckLogin(Scanner sc) {
        String username = InputValidator.inputString(sc, "Tên đăng nhập:\n");
        String password = InputValidator.inputString(sc, "Mật khẩu;\n");

        AdminDAOImpl adminDAO = new AdminDAOImpl();

        Admin admin = adminDAO.login(username, password);

        if (admin != null) {
            System.out.println("\n\n\n===========================================");
            System.out.println("Đăng nhập thành công tài khoản");
            System.out.printf("""
                    Quyền Admin
                    ID: %d
                    Tên: %s
                    """, admin.getId(), admin.getUsername());
            return true;
        } else  {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng");
            return false;
        }
    }
}
