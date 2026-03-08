package dao;

import model.Student;

import java.util.List;

public interface IStudentManagerDAO {
    boolean existsByEmail(String email);
    List<Student> filterStudent(int option, String Data);
}
