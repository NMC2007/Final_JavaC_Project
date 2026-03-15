package dao;

import model.StatisticalCourse;
import model.StatisticalCourseAndStudent;

import java.util.List;

public interface IStatisticalDAO {
    StatisticalCourseAndStudent getTotalCoursesAndStudents();
    List<StatisticalCourse>  getStudentCountByCourse();
    List<StatisticalCourse>  getTop5CoursesByStudentCount();
    List<StatisticalCourse>  getCoursesWithMoreThan10Students();
}
