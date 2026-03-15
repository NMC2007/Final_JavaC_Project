package business.impl;

import business.EnrollmentService;
import utils.tableConfig.CourseTableView;
import utils.tableConfig.StudentStatusTableView;
import utils.tableConfig.StudentTableView;
import dao.impl.CourseManagerDAOImpl;
import dao.impl.EnrollmentManagerDAOImpl;
import dao.impl.StudentManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Course;
import model.Enrollment;
import model.Student;
import model.StudentStatus;
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

            List<StudentStatus> listStudentStatus = enrollmentDAO.getStudentsBelongToCourse(idCourse);
            if (listStudentStatus.isEmpty()) {
                System.out.println("Chưa có học sinh đăng ký hoá học này.");
            } else {
                updateStatus(sc, listStudentStatus, idCourse);
            }
        }
    }

    private void updateStatus(Scanner sc, List<StudentStatus> listStudentStatus, int idCourse) {

        while (true) {

            boolean hasWaiting = listStudentStatus.stream()
                    .anyMatch(s -> s.getStatus().equalsIgnoreCase("WAITING"));

            if (!hasWaiting) {
                System.out.println("Danh sách học sinh đăng ký khoá học này:");
                StudentStatusTableView.printListStudentStatus(listStudentStatus);
                System.out.println("✅ Không còn sinh viên đang chờ duyệt.");
                return;
            }

            System.out.println("Danh sách học sinh đăng ký khoá học này:");
            StudentStatusTableView.printListStudentStatus(listStudentStatus);

            System.out.println("""
                1. Duyệt sinh viên
                2. Thoát
                """);

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 2);

            if (choice == 2) {
                return;
            }

            int studentId = InputValidator.inputInt(sc, "Nhập ID sinh viên cần duyệt: ");

            StudentStatus target = null;

            for (StudentStatus s : listStudentStatus) {
                if (s.getId() == studentId) {
                    target = s;
                    break;
                }
            }

            if (target == null) {
                System.out.println("❌ Sinh viên không đăng ký khóa học này.");
                continue;
            }

            if (!target.getStatus().equalsIgnoreCase("WAITING")) {
                System.out.println("⚠️ Sinh viên này đã được xử lý.");
                continue;
            }

            System.out.println("""
                1. Duyệt (CONFIRM)
                2. Từ chối (CANCEL)
                """);

            int action = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 2);

            UpdateStatusEnum result;

            if (action == 1) {
                result = enrollmentDAO.updateStatusStudent(studentId, idCourse, "CONFIRM");
                if (result == UpdateStatusEnum.SUCCESS) {
                    System.out.println("✅ Đã duyệt sinh viên.");
                    target.setStatus("CONFIRM");
                } else {
                    System.out.println("❌ Lỗi! Không thể duyệt sinh viên.");
                }
            } else {
                result = enrollmentDAO.updateStatusStudent(studentId, idCourse, "CANCEL");
                if (result == UpdateStatusEnum.SUCCESS) {
                    System.out.println("✅ Đã từ chối sinh viên.");
                    target.setStatus("CANCEL");
                } else {
                    System.out.println("❌ Lỗi! Không thể từ chối sinh viên.");
                }
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
