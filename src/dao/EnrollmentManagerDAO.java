package dao;

import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import model.Enrollment;
import model.Student;

import java.util.List;

public interface EnrollmentManagerDAO {
    List<Student> getStudentsBelongToCourse(int courseId);
    InsertStatusEnum addStudentToTheCourse(Enrollment enrollment);
    DeleteStatusEnum deleteStudentFromTheCourse(int idStudent, int idCourse);
}
