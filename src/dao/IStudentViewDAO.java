package dao;

import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Course;
import model.Enrollment;
import model.EnrollmentView;

import java.util.List;

public interface IStudentViewDAO {
    List<Course> showListCourse();
    List<EnrollmentView> showListEnrollments(int idStudent);
    InsertStatusEnum createEnrollment(Enrollment enrollment);
    DeleteStatusEnum deleteEnrollment(int idStudent, int idCourse);
    boolean checkedPassword(int idStudent, String oldPassword);
    UpdateStatusEnum updatePassword(int idStudent, String password);
}
