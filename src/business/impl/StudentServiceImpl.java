package business.impl;

import business.StudentService;
import business.TableView.StudentTableView;
import dao.impl.StudentManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Student;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {
    private static final StudentManagerDAOImpl studentDAO = new StudentManagerDAOImpl();
    @Override
    public void showData() {
        List<Student> studentList = studentDAO.findAll();

        if (studentList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có sinh viên nào.");
        } else {
            StudentTableView.printListStudents(studentList);
        }
    }

    @Override
    public void createData(Scanner sc) {
        Student student = new Student();

        student.inputData(sc);

        while (studentDAO.existsByEmail(student.getEmail())) {

            System.out.println("❌ Email đã tồn tại!");

            System.out.println("""
                    1. Nhập lại email
                    2. Huỷ thêm sinh viên
                    """);

            int choice = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 2);

            if (choice == 2) {
                System.out.println("⚠️ Đã hủy thêm sinh viên.");
                return;
            }

            String newEmail = InputValidator.inputEmail(sc, "Nhập email mới: ");
            student.setEmail(newEmail);
        }

        InsertStatusEnum result = studentDAO.insert(student);

        switch (result) {
            case SUCCESS:
                System.out.println("✅ Thêm sinh viên thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể thêm Sinh viên.");
                break;
        }
    }

    @Override
    public void updateData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần sửa: ");
        Student student = studentDAO.findById(id);

        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);

            Student updatedStudent = new Student();

            // copy dữ liệu cũ
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

                            if (studentDAO.existsByEmail(updatedStudent.getEmail())) {
                                System.out.println("❌ Email đã tồn tại! Không thể lưu thay đổi.");
                                return;
                            }
                        }

                        UpdateStatusEnum result = studentDAO.update(updatedStudent);
                        switch (result) {
                            case SUCCESS:
                                System.out.println("✅ Cập nhật sinh viên thành công!");
                                break;
                            case ERROR:
                                System.out.println("❌ Lỗi! Không thể cập nhật dữ liệu sinh viên.");
                                break;
                        }

                        return;

                    case 7:
                        System.out.println("⚠️ Đã hủy cập nhật.");
                        return;
                }
            }
        }
    }

    @Override
    public void deleteData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần xoá: ");

        Student student = studentDAO.findById(id);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);

            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                DeleteStatusEnum result = studentDAO.delete(id);
                switch (result) {
                    case SUCCESS:
                        System.out.println("✅ Xoá sinh viên thành công!");
                        break;
                    case ERROR:
                        System.out.println("❌ Lỗi! Không thể xoá sinh viên.");
                }
            }
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

        List<Student> StudentList = studentDAO.sort(option);

        if (StudentList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có sinh viên nào.");
        } else {
            System.out.println("Danh sách sinh viên sau khi sắp xếp:");;
            StudentTableView.printListStudents(StudentList);
        }
    }

    @Override
    public void filterStudent(Scanner sc) {
        System.out.println("""
                =================== Tìm kiếm ===================
                1. Tìm theo tên
                2. Tìm theo email
                3. Tìm theo ID
                """);

        int option = InputValidator.inputMenu(sc, "Nhập lựa chọn: ", 3);

        List<Student> studentList;

        switch (option) {
            case 1:
                String name = InputValidator.inputString(sc, "Nhập tên sinh viên cần tìm: ");
                studentList = studentDAO.filterStudent(1, name);
                if (studentList.isEmpty()) {
                    System.out.println("Không tìm thấy sinh viên nào.");
                } else {
                    System.out.println("✅ Tìm thấy sinh viên:");
                    StudentTableView.printListStudents(studentList);
                }
                break;
            case 2:
                String email = InputValidator.inputEmail(sc, "Nhập email sinh viên cần tìm: ");
                studentList = studentDAO.filterStudent(2, email);
                if (studentList.isEmpty()) {
                    System.out.println("Không tìm thấy sinh viên nào.");
                } else {
                    System.out.println("✅ Tìm thấy sinh viên:");
                    StudentTableView.printListStudents(studentList);
                }
                break;
            case 3:
                int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần tìm: ");
                Student student = studentDAO.findById(id);
                if (student == null) {
                    System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
                } else {
                    System.out.println("✅ Tìm thấy sinh viên:");
                    StudentTableView.printStudent(student);
                }
                break;
        }
    }
}
