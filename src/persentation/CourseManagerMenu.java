package persentation;

import business.impl.CourseServiceImpl;
import dao.impl.CourseManagerDAOImpl;
import model.Course;
import utils.tableConfig.CourseTableView;
import validation.InputValidator;

import java.util.Scanner;

public class CourseManagerMenu {
    private static final CourseServiceImpl courseService = new CourseServiceImpl();
    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("""
                    \n\n============================= MENU QUẢN LÝ KHOÁ HỌC =============================
                    1. Hiển thị danh sách khóa học
                    2. Thêm mới khóa học
                    3. Chỉnh sửa thông tin khóa học
                    4. Xóa khóa học
                    5. Tìm kiếm theo tên
                    6. Sắp xếp danh sách khoá học
                    7. Quay về menu chính
                    """);

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
                    filterByNameCourse(sc);
                    break;
                case 6:
                    sortCourse(sc);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }


    private static void showCourse() {
        courseService.showData();
    }

    private static void createCourse(Scanner sc) {
        courseService.createData(sc);
    }


    private static void updateCourse(Scanner sc) {
        int id = InputValidator.inputInt(sc, "\nNhập ID khoá học cần sửa: ");

        Course course = courseService.findById(id);

        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);
            while (true) {

                System.out.println("""
                        \n============================= CHỌN THÔNG TIN CẦN SỬA =============================
                        1. Sửa tên khóa học
                        2. Sửa thời lượng
                        3. Sửa giảng viên
                        4. Lưu thay đổi
                        5. Hủy
                        """);

                int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 5);

                switch (choice) {
                    case 1:
                        course.setName(
                                InputValidator.inputString(sc, "Nhập tên khóa học mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;
                    case 2:
                        course.setDuration(
                                InputValidator.inputInt(sc, "Nhập thời lượng mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;
                    case 3:
                        course.setInstructor(
                                InputValidator.inputString(sc, "Nhập tên giảng viên mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;
                    case 4:
                        courseService.updateData(course);
                        return;
                    case 5:
                        System.out.println("⚠️ Hủy cập nhật.");
                        return;
                }
            }
        }
    }

    private static void deleteCourse(Scanner sc) {
        int id = InputValidator.inputInt(sc, "\nNhập ID khoá học cần xoá: ");

        Course course = courseService.findById(id);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);

            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                courseService.deleteData(id);
            }
        }
    }

    private static void filterByNameCourse(Scanner sc) {
        String name = InputValidator.inputString(sc, "Nhập tên cần tìm: ");
        courseService.filterCourseByName(name);
    }

    private static void sortCourse(Scanner sc) {
        System.out.println("""
                \n============================= SẮP XẾP =============================
                1. ID tăng dần
                2. ID giảm dần
                3. Tên A->Z
                4. Tên Z-A
                """);

        int option = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 4);

        courseService.sortData(option);
    }

}
