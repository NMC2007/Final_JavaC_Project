package business.impl;


import business.StudentViewService;
import dao.impl.CourseManagerDAOImpl;
import dao.impl.StudentManagerDAOImpl;
import dao.impl.StudentViewDAOImpl;
import model.Course;
import model.Enrollment;
import model.EnrollmentView;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentViewServiceImpl implements StudentViewService {
    private static final CourseManagerDAOImpl courseDAO = new CourseManagerDAOImpl();

    private static final StudentViewDAOImpl studentViewDAO = new StudentViewDAOImpl();

    @Override
    public void showCourse() {
        List<Course> courseList = studentViewDAO.showListCourse();
        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào");
        } else {
            System.out.println("\nDanh sách các khoá học");
            printTableCourse(courseList);
        }
    }

    @Override
    public void showEnrollment(int idStudent) {
        List<EnrollmentView> enrollmentList = studentViewDAO.showListEnrollments(idStudent);
        if (enrollmentList.isEmpty()) {
            System.out.println("Bạn chưa đăng ký khoá học nào");
        } else {
            System.out.println("\nCác khoá học đã đăng ký:");
            printTableEnrollment(enrollmentList);
        }
    }

    @Override
    public void createEnrollment(Scanner sc, int idStudent) {
//        tạo xong thêm id vào rồi cho nhập id khoá học sv tự chọn
        int idCourse = InputValidator.inputInt(sc, "Nhập id khoá học muốn đăng ký: ");
        if (courseDAO.findById(idCourse) == null) {
            System.out.println("❌ Không tìm thấy khoá học với id = " + idCourse);
        } else {
            Enrollment newEnrollment = new Enrollment();
            newEnrollment.inputData(idStudent ,idCourse);

            studentViewDAO.createEnrollment(newEnrollment);
        }

    }

    @Override
    public void deleteEnrollment(Scanner sc, int idStudent) {
        int idCourse = InputValidator.inputInt(sc, "Nhập id khoá học muốn xoá: ");

        studentViewDAO.deleteEnrollment(idStudent, idCourse);
    }

    @Override
    public void updatePassword(Scanner sc, int idStudent) {
        String oldPassword = InputValidator.inputString(sc, "Nhập mật khẩu hiện tại: ");

        boolean isCorrect = studentViewDAO.checkedPassword(idStudent, oldPassword);

        if (!isCorrect) {
            System.out.println("❌ Mật khẩu hiện tại không đúng.");
            return;
        }

        String newPassword;
        String confirmPassword;

        while (true) {

            newPassword = InputValidator.inputString(sc, "Nhập mẩt khẩu mới: ");
            confirmPassword = InputValidator.inputString(sc, "Xác nhận mật khẩu: ");

            if (!newPassword.equals(confirmPassword)) {
                System.out.println("❌ Mật khẩu xác nhận không khớp, nhập lại");
            } else {
                break;
            }
        }

        studentViewDAO.updatePassword(idStudent, newPassword);
    }


    private static void printTableEnrollment(List<EnrollmentView> enrollmentList) {
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("| %-25s | %-35s | %-10s |\n", "Course Name", "Registered At", "Status");
        System.out.println("------------------------------------------------------------------------");

        for (EnrollmentView enrollment : enrollmentList) {
            enrollment.display();
        }

        System.out.println("------------------------------------------------------------------------");
    }

    private static void printTableCourse(List<Course> courseList) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-25s | %-10s | %-20s | %-12s |\n",
                "ID", "NAME", "DURATION", "INSTRUCTOR", "CREATED");
        System.out.println("----------------------------------------------------------------------------------------");

        for (Course c : courseList) {
            c.displayData();
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }
}
