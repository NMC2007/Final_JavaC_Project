package business.impl;

import business.CourseService;
import dao.impl.CourseDAOImpl;
import model.Course;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class CourseServiceImpl implements CourseService {
    private static final CourseDAOImpl courseDAO = new CourseDAOImpl();

    @Override
    public void showData() {
        List<Course> courseList = courseDAO.findAll();
        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào");
        } else {
            printTable(courseList);
        }
    }

    @Override
    public void createData(Scanner sc) {
        Course newCourse = new Course();

        newCourse.inputData(sc);

        courseDAO.insert(newCourse);
    }

    @Override
    public void updateData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID khoá học cần sửa: ");

        Course course = courseDAO.findById(id);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        } else {
            while (true) {

                System.out.println("\nChọn thông tin cần sửa:");
                System.out.println("1. Sửa tên khóa học");
                System.out.println("2. Sửa thời lượng");
                System.out.println("3. Sửa giảng viên");
                System.out.println("4. Lưu thay đổi");
                System.out.println("5. Hủy");

                int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 5);

                switch (choice) {

                    case 1:
                        course.setName(
                                InputValidator.inputString(sc, "Nhập tên khóa học mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi");
                        break;

                    case 2:
                        course.setDuration(
                                InputValidator.inputInt(sc, "Nhập thời lượng mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi");
                        break;

                    case 3:
                        course.setInstructor(
                                InputValidator.inputString(sc, "Nhập tên giảng viên mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi");
                        break;

                    case 4:
                        courseDAO.update(course);
                        return;

                    case 5:
                        System.out.println("❌ Hủy cập nhật");
                        return;
                }
            }
        }
    }

    @Override
    public void deleteData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID khoá học cần xoá: ");

        Course course = courseDAO.findById(id);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        } else {
            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                courseDAO.delete(id);
            }
        }
    }

    @Override
    public void filterCourseByName(Scanner sc) {
        String name = InputValidator.inputString(sc, "Nhập tên cần tìm: ");

        List<Course> courseList = courseDAO.filterByName(name);

        if (courseList.isEmpty()) {
            System.out.println("Không tìm thấy khoá học có tên " + name);
        } else {
            System.out.println("Khoá học tìm được:");;
            printTable(courseList);
        }
    }

    @Override
    public void sortData(Scanner sc) {
        System.out.println("""
                =================== Sắp xếp ===================
                1. ID tăng dần
                2. ID giảm dần
                3. Tên A->Z
                4. Tên Z-A
                """);

        int option = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 4);

        List<Course> courseList = courseDAO.sort(option);

        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào");
        } else {
            System.out.println("Khoá học tìm được:");;
            printTable(courseList);
        }
    }

    private static void printTable(List<Course> courseList) {
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
