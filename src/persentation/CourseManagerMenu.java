package persentation;

import dao.CourseDAOImpl;
import model.Course;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class CourseManagerMenu {
    private static final CourseDAOImpl courseDAO = new CourseDAOImpl();
    public static void showMenu(Scanner sc) {
        while (true) {

            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Sắp xếp theo tên");
            System.out.println("7. Quay về menu chính");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn của bạn: ", 7);

            switch (choice) {

                case 1:
                    showCourse();
                    break;

                case 2:
                    createCourse(sc);
                    break;

                case 3:
                    updateCourse(sc);
                    break;

                case 4:
                    deleteCourse(sc);
                    break;

                case 5:
                    // tìm kiếm theo tên
                    break;

                case 6:
                    // sắp xếp theo tên
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }


    private static void showCourse() {
        List<Course> courseList = courseDAO.findAll();

        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào");
        } else {
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

    private static void createCourse(Scanner sc) {
        Course newCourse = new Course();

        newCourse.inputData(sc);

        courseDAO.insert(newCourse);
    }


    private static void updateCourse(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID khoá học cần sửa: ");
        courseDAO.update(id, sc);
    }

    private static void deleteCourse(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID khoá học cần xoá: ");

        courseDAO.delete(id, sc);
    }

}
