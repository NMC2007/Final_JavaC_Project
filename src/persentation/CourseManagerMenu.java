package persentation;

import business.impl.CourseServiceImpl;
import validation.InputValidator;

import java.util.Scanner;

public class CourseManagerMenu {
    private static final CourseServiceImpl courseService = new CourseServiceImpl();
    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Sắp xếp danh sách khoá học");
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
        courseService.updateData(sc);
    }

    private static void deleteCourse(Scanner sc) {
        courseService.deleteData(sc);
    }

    private static void filterByNameCourse(Scanner sc) {
        courseService.filterCourseByName(sc);
    }

    private static void sortCourse(Scanner sc) {
        courseService.sortData(sc);
    }

}
