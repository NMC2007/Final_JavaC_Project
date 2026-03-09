package business.impl;

import business.EnrollmentService;
import business.TableView.CourseTableView;
import business.TableView.StudentTableView;
import dao.impl.CourseManagerDAOImpl;
import dao.impl.EnrollmentManagerDAOImpl;
import dao.impl.StudentManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import model.Course;
import model.Enrollment;
import model.Student;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class EnrollmentServiceImpl implements EnrollmentService {
    private static final CourseManagerDAOImpl courseDAO = new CourseManagerDAOImpl();
    private static final StudentManagerDAOImpl studentDAO = new StudentManagerDAOImpl();

    private static final EnrollmentManagerDAOImpl enrollmentDAO = new EnrollmentManagerDAOImpl();

    @Override
    public void showListStudentsBelongToCourse(Scanner sc) {
        int idCourse = InputValidator.inputInt(sc, "\nNhập id khoá học cần hiển thị danh sách sinh viên: ");
        Course course = courseDAO.findById(idCourse);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khoá học với id = " + idCourse);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);

            List<Student> listStudent = enrollmentDAO.getStudentsBelongToCourse(idCourse);
            if (listStudent.isEmpty()) {
                System.out.println("Chưa có học sinh đăng ký hoá học này.");
            } else {
                System.out.println("Danh sách học sinh đăng ký khoá học này:");
                StudentTableView.printListStudents(listStudent);
            }
        }
    }

    @Override
    public void addStudentToTheCourse(Scanner sc) {
        boolean findStudent = false;
        boolean findCourse = false;

        int idStudent = InputValidator.inputInt(sc, "Nhập id sinh viên: ");
        Student student = studentDAO.findById(idStudent);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + idStudent);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);
            findStudent = true;
        }

        int idCourse = InputValidator.inputInt(sc, "\nNhập id khoá học muốn đăng ký: ");
        Course course = courseDAO.findById(idCourse);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khoá học với id = " + idCourse);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);
            findCourse = true;
        }

        if (findStudent && findCourse) {
            Enrollment enrollment = new Enrollment();
            enrollment.inputData(idStudent, idCourse);

            InsertStatusEnum result = enrollmentDAO.addStudentToTheCourse(enrollment);
            switch (result) {
                case SUCCESS:
                    System.out.println("✅ Thêm thành công sinh viên vào khoá học.");
                    break;
                case ERROR:
                    System.out.println("❌ Lỗi! không thể thêm sinh viên vào khoá học.");
                    break;
            }
        } else {
            System.out.println("❌ id sinh viên hoặc id khoá học không hợp lệ.");
        }
    }

    @Override
    public void deleteStudentFromTheCourse(Scanner sc) {
        boolean findStudent = false;
        boolean findCourse = false;

        int idCourse = InputValidator.inputInt(sc, "\nNhập id khoá học: ");
        Course course = courseDAO.findById(idCourse);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khoá học với id = " + idCourse);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);
            findCourse = true;
        }

        int idStudent = InputValidator.inputInt(sc, "Nhập id sinh viên muốn xoá khỏi khoá học: ");
        Student student = studentDAO.findById(idStudent);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + idStudent);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);
            findStudent = true;
        }

        if (findStudent && findCourse) {
            DeleteStatusEnum result = enrollmentDAO.deleteStudentFromTheCourse(idStudent, idCourse);
            switch (result) {
                case SUCCESS:
                    System.out.println("✅ Xoá thành công sinh viên khỏi khoá học.");
                    break;
                case DOSE_NOT_EXIST:
                    System.out.println("❌ Sinh viên không đăng ký khoá học này.");
                    break;
                case ERROR:
                    System.out.println("❌ Lỗi! không thể xoá sinh viên khỏi khoá học.");
                    break;
            }
        } else {
            System.out.println("❌ id sinh viên hoặc id khoá học không hợp lệ.");
        }
    }
}
