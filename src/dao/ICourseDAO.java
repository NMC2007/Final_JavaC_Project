package dao;

import model.Course;

import java.util.List;

public interface ICourseDAO {
    List<Course> filterByName(String courseName);
}
