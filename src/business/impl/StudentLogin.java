package business.impl;

import business.ILoginBusiness;
import dao.impl.StudentDAOImpl;
import model.Student;
import validation.InputValidator;

import java.util.Scanner;

public class StudentLogin implements ILoginBusiness {
    @Override
    public boolean CheckLogin(Scanner sc) {
        String email = InputValidator.inputEmail(sc, "Email sinh viên:\n");
        String password = InputValidator.inputString(sc, "Mật khẩu:\n");

        StudentDAOImpl studentDAO = new StudentDAOImpl();
        Student student = studentDAO.login(email, password);

        if (student != null) {

            System.out.println("\n\n\n===========================================");
            System.out.println("Đăng nhập thành công");

            System.out.printf("""
                    Quyền Sinh viên
                    ID: %d
                    Tên: %s
                    Ngày sinh: %s
                    Email: %s
                    """, student.getId(), student.getName(), student.getDob(), student.getEmail());

            return true;
        } else {
            System.out.println("Email hoặc mật khẩu không đúng");
            return false;
        }
    }
}
