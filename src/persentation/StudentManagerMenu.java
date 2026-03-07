package persentation;

import business.impl.StudentServiceImpl;
import validation.InputValidator;

import java.util.Scanner;

public class StudentManagerMenu {
    private static final StudentServiceImpl studentService = new StudentServiceImpl();
    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Chỉnh sửa thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm theo tên / email / id");
            System.out.println("6. Sắp xếp danh sách sinh viên");
            System.out.println("7. Quay về menu chính");

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn của bạn: ", 7);

            switch (choice) {

                case 1:
                    showStudent();
                    break;

                case 2:
                    createStudent(sc);
                    break;

                case 3:
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    searchStudent(sc);
                    break;

                case 6:
                    sortStudent(sc);
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // hiển thị danh sách
    private static void showStudent() {
        studentService.showData();
    }

    // thêm mới
    private static void createStudent(Scanner sc) {
        studentService.createData(sc);
    }

    // update
    private static void updateStudent(Scanner sc) {
        studentService.updateData(sc);
    }

    // delete
    private static void deleteStudent(Scanner sc) {
        studentService.deleteData(sc);
    }

    // tìm kiếm
    private static void searchStudent(Scanner sc) {
        studentService.filterStudent(sc);
    }

    // sắp xếp
    private static void sortStudent(Scanner sc) {
        studentService.sortData(sc);
    }
}