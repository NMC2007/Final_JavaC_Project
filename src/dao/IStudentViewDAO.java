package dao;

import model.Course;
import model.Enrollment;
import model.EnrollmentView;

import java.util.List;

public interface IStudentViewDAO {
    List<Course> showListCourse();
    List<EnrollmentView> showListEnrollments(int idStudent);

    void createEnrollment(Enrollment enrollment);

    void deleteEnrollment(int idStudent, int idCourse);

    boolean checkedPassword(int idStudent, String oldPassword);

    void updatePassword(int idStudent, String password);
}
