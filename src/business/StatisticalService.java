package business;

public interface StatisticalService {
    void getTotalCoursesAndStudents();
    void getStudentCountByCourse();
    void getTop5CoursesByStudentCount();
    void getCoursesWithMoreThan10Students();
}
