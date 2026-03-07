package persentation;

import dao.impl.StudentDAOImpl;
import model.Student;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentManagerMenu {

    private static final StudentDAOImpl studentDAO = new StudentDAOImpl();

    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm kiếm theo tên / email / id");
            System.out.println("6. Sắp xếp theo id");
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

        List<Student> studentList = studentDAO.findAll();

        if (studentList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có học viên nào");
        } else {

            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
                    "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            for (Student s : studentList) {
                s.displayData();
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------");
        }
    }

    // thêm mới
    private static void createStudent(Scanner sc) {

        Student student = new Student();

        student.inputData(sc);

        studentDAO.insert(student);
    }

    // update
    private static void updateStudent(Scanner sc) {

        int id = InputValidator.inputInt(sc, "Nhập ID học viên cần sửa: ");

        studentDAO.update(id, sc);
    }

    // delete
    private static void deleteStudent(Scanner sc) {

        int id = InputValidator.inputInt(sc, "Nhập ID học viên cần xoá: ");

        studentDAO.delete(id, sc);
    }

    // tìm kiếm
    private static void searchStudent(Scanner sc) {

//        List<Student> studentList = studentDAO.findAll();
//
//        System.out.print("Nhập từ khóa tìm kiếm: ");
//        String keyword = sc.nextLine().toLowerCase();
//
//        boolean found = false;
//
//        for (Student s : studentList) {
//
//            if (String.valueOf(s.getId()).contains(keyword)
//                    || s.getName().toLowerCase().contains(keyword)
//                    || s.getEmail().toLowerCase().contains(keyword)) {
//
//                if (!found) {
//
//                    System.out.println("--------------------------------------------------------------------------------------------------------------------");
//                    System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
//                            "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
//                    System.out.println("--------------------------------------------------------------------------------------------------------------------");
//
//                    found = true;
//                }
//
//                s.displayData();
//            }
//        }
//
//        if (!found) {
//            System.out.println("❌ Không tìm thấy học viên phù hợp");
//        } else {
//            System.out.println("--------------------------------------------------------------------------------------------------------------------");
//        }
    }

    // sắp xếp
    private static void sortStudent(Scanner sc) {
//
//        List<Student> studentList = studentDAO.findAll();
//
//        System.out.println("1. Tăng dần theo ID");
//        System.out.println("2. Giảm dần theo ID");
//
//        int choice = InputValidator.inputMenu(sc, "Chọn kiểu sắp xếp: ", 2);
//
//        if (choice == 1) {
//            studentList.sort(Comparator.comparingInt(Student::getId));
//        } else {
//            studentList.sort(Comparator.comparingInt(Student::getId).reversed());
//        }
//
//        System.out.println("--------------------------------------------------------------------------------------------------------------------");
//        System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
//                "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
//        System.out.println("--------------------------------------------------------------------------------------------------------------------");
//
//        for (Student s : studentList) {
//            s.displayData();
//        }
//
//        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}