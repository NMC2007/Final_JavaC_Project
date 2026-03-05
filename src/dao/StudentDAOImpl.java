package dao;

import dao.impl.ICourseCRUD;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentDAOImpl implements ICourseCRUD<Student> {

    @Override
    public void insert(Student student) {

    }

    @Override
    public void update(int id, Scanner sc) {

    }

    @Override
    public void delete(int id, Scanner sc) {

    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }
}
