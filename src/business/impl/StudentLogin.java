package business.impl;

import business.ILoginBusiness;
import dao.impl.StudentManagerDAOImpl;
import model.Student;

public class StudentLogin implements ILoginBusiness<Student> {
    private static final StudentManagerDAOImpl studentManagerDAO = new StudentManagerDAOImpl();
    @Override
    public Student CheckLogin(String email, String password) {
        return studentManagerDAO.login(email, password);
    }
}
