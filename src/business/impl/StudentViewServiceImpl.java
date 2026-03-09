package business.impl;

import business.StudentViewService;
import business.TableView.CourseTableView;
import business.TableView.EnrollmentTableView;
import dao.impl.CourseManagerDAOImpl;
import dao.impl.StudentViewDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
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
            System.out.println("\nDanh sách hiện chưa có khoá học nào.");
        } else {
            System.out.println("\nDanh sách các khoá học:");
            CourseTableView.printListCourses(courseList);
        }
    }

    @Override
    public void showEnrollment(int idStudent) {
        List<EnrollmentView> enrollmentList = studentViewDAO.showListEnrollments(idStudent);
        if (enrollmentList.isEmpty()) {
            System.out.println("\nBạn chưa đăng ký khoá học nào.");
        } else {
            System.out.println("\nCác khoá học đã đăng ký:");
            EnrollmentTableView.printListEnrollment(enrollmentList);
        }
    }

    @Override
    public void createEnrollment(Scanner sc, int idStudent) {
//        tạo xong thêm id vào rồi cho nhập id khoá học sv tự chọn
        int idCourse = InputValidator.inputInt(sc, "Nhập id khoá học muốn đăng ký: ");
        Course course = courseDAO.findById(idCourse);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khoá học với id = " + idCourse);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);

            Enrollment newEnrollment = new Enrollment();

            newEnrollment.inputData(idStudent ,idCourse);

            InsertStatusEnum result = studentViewDAO.createEnrollment(newEnrollment);
            switch (result) {
                case SUCCESS:
                    System.out.println("✅ Đăng ký thành công!");
                    break;
                case ERROR:
                    System.out.println("❌ Đăng ký không thành công!");
                    break;
            }
        }
    }

    @Override
    public void deleteEnrollment(Scanner sc, int idStudent) {

        List<EnrollmentView> enrollmentList = studentViewDAO.showListEnrollments(idStudent);
        if (enrollmentList.isEmpty()) {
            System.out.println("\nBạn chưa đăng ký khoá học nào.");
        } else {
            System.out.println("\nCác khoá học đã đăng ký:");
            EnrollmentTableView.printListEnrollment(enrollmentList);

            int idCourse = InputValidator.inputInt(sc, "Nhập id khoá học muốn huỷ: ");

            DeleteStatusEnum result = studentViewDAO.deleteEnrollment(idStudent, idCourse);
            switch (result) {
                case SUCCESS:
                    System.out.println("✅ Huỷ đăng ký khóa học thành công!");
                    break;
                case DOSE_NOT_EXIST:
                    System.out.println("❌ Bạn chưa đăng ký khóa học này.");
                    break;
                case UNAUTHORIZED:
                    System.out.println("❌ Khóa học không thể huỷ.\n(Chỉ huỷ được khi còn trong trạng thái chờ)");
                    break;
                case ERROR:
                    System.out.println("❌ Lỗi! Không thể huỷ khoá học.");
                    break;
            }
        }
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
                System.out.println("❌ Mật khẩu xác nhận không khớp, nhập lại.");
            } else {
                break;
            }
        }

        UpdateStatusEnum result = studentViewDAO.updatePassword(idStudent, newPassword);

        switch (result) {
            case SUCCESS:
                System.out.println("✅ Thay đổi mật khẩu thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể thay đổi mật khẩu.");
                break;
        }
    }
}
