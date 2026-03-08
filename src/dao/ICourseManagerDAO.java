package dao;

import model.Course;

import java.util.List;

public interface ICourseManagerDAO {
    List<Course> filterByName(String courseName);
}
