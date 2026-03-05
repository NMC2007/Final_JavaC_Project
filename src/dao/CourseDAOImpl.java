package dao;

import dao.impl.ICourseCRUD;
import model.Course;

import java.util.List;

public class CourseDAOImpl implements ICourseCRUD<Course> {

    @Override
    public void insert(Course course) {

    }

    @Override
    public void update(int id, Course course) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Course findById(int id) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }
}
