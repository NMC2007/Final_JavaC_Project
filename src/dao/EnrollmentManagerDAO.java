package dao;

import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Enrollment;
import model.StudentStatus;

import java.util.List;

public interface EnrollmentManagerDAO {
    List<StudentStatus> getStudentsBelongToCourse(int courseId);
    InsertStatusEnum addStudentToTheCourse(Enrollment enrollment);
    DeleteStatusEnum deleteStudentFromTheCourse(int idStudent, int idCourse);
    UpdateStatusEnum updateStatusStudent (int idStudent, int idCourse, String status);
}
