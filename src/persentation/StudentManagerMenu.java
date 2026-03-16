package persentation;

import business.impl.StudentServiceImpl;
import dao.impl.StudentManagerDAOImpl;
import model.Student;
import utils.tableConfig.StudentTableView;
import validation.InputValidator;

import java.util.Scanner;

public class StudentManagerMenu {
    private static final StudentServiceImpl studentService = new StudentServiceImpl();
    public static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== MENU QUẢN LÝ SINH VIÊN =====");
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
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần sửa: ");
        Student student = studentService.findById(id);

        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);

            Student updatedStudent = new Student();

            updatedStudent.setId(student.getId());
            updatedStudent.setName(student.getName());
            updatedStudent.setDob(student.getDob());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setSex(student.isSex());
            updatedStudent.setPhone(student.getPhone());
            updatedStudent.setPassword(student.getPassword());

            while (true) {

                int choice = InputValidator.inputMenu(
                        sc,
                        "\n===== CHỌN THÔNG TIN CẦN SỬA =====\n" +
                                "1. Sửa tên\n" +
                                "2. Sửa ngày sinh\n" +
                                "3. Sửa email\n" +
                                "4. Sửa giới tính\n" +
                                "5. Sửa số điện thoại\n" +
                                "6. Lưu thay đổi\n" +
                                "7. Hủy\n" +
                                "Lựa chọn của bạn: ",
                        7
                );

                switch (choice) {

                    case 1:
                        updatedStudent.setName(
                                InputValidator.inputString(sc, "Nhập tên mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;

                    case 2:
                        updatedStudent.setDob(
                                InputValidator.inputDate(sc, "Nhập ngày sinh mới (dd/MM/yyyy): ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;

                    case 3:
                        updatedStudent.setEmail(
                                InputValidator.inputEmail(sc, "Nhập email mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;

                    case 4:
                        int sexChoice = InputValidator.inputMenu(
                                sc,
                                "Chọn giới tính:\n1. Nam\n2. Nữ\nLựa chọn: ",
                                2
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        updatedStudent.setSex(sexChoice == 1);
                        break;

                    case 5:
                        updatedStudent.setPhone(
                                InputValidator.inputPhone(sc, "Nhập số điện thoại mới: ")
                        );
                        System.out.println("✅ Ghi nhận thay đổi.");
                        break;

                    case 6:
                        // kiểm tra email nếu có thay đổi
                        if (!updatedStudent.getEmail().equals(student.getEmail())) {
                            if (studentService.existsByEmail(updatedStudent.getEmail())) {
                                System.out.println("❌ Email đã tồn tại! Không thể lưu thay đổi.");
                                return;
                            }
                        }
                        studentService.updateData(student);
                        return;

                    case 7:
                        System.out.println("⚠️ Đã hủy cập nhật.");
                        return;
                }
            }
        }
    }

    // delete
    private static void deleteStudent(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần xoá: ");

        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);

            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                studentService.deleteData(id);
            }
        }
    }

    // tìm kiếm
    private static void searchStudent(Scanner sc) {
        System.out.println("""
                =================== Tìm kiếm ===================
                1. Tìm theo tên
                2. Tìm theo email
                3. Tìm theo ID
                """);

        int option = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 3);

        switch (option) {
            case 1:
                String name = InputValidator.inputString(sc, "Nhập tên sinh viên cần tìm: ");
                studentService.filterStudent(1, name);
                break;
            case 2:
                String email = InputValidator.inputEmail(sc, "Nhập email sinh viên cần tìm: ");
                studentService.filterStudent(1, email);
                break;
            case 3:
                int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần tìm: ");
                studentService.filterStudent(id);
                break;
        }
    }

    // sắp xếp
    private static void sortStudent(Scanner sc) {
        System.out.println("""
                =================== Sắp xếp ===================
                1. ID tăng dần
                2. ID giảm dần
                3. Tên A->Z
                4. Tên Z-A
                """);

        int option = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 4);
        studentService.sortData(option);
    }
}