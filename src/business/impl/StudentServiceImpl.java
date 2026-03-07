package business.impl;

import business.StudentService;
import dao.impl.StudentDAOImpl;
import model.Student;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {
    private static final StudentDAOImpl studentDAO = new StudentDAOImpl();
    @Override
    public void showData() {
        List<Student> studentList = studentDAO.findAll();

        if (studentList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có sinh viên nào");
        } else {
            printTable(studentList);
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

        studentDAO.insert(student);
    }

    @Override
    public void updateData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần sửa: ");

    }

    @Override
    public void deleteData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần xoá: ");

        Student student = studentDAO.findById(id);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                studentDAO.delete(id);
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
            System.out.println("Danh sách hiện chưa có khoá học nào");
        } else {
            System.out.println("Khoá học tìm được:");;
            printTable(StudentList);
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

        List<Student> studentList = studentDAO.findAll();

        switch (option) {
            case 1:
                String name = InputValidator.inputString(sc, "Nhập tên sinh viên cần tìm: ");
                studentList = studentDAO.filterStudent(1, name);
                if (studentList.isEmpty()) {
                    System.out.println("Không tìm thấy sinh viên nào");
                } else {
                    printTable(studentList);
                }
                break;
            case 2:
                String email = InputValidator.inputEmail(sc, "Nhập email sinh viên cần tìm: ");
                studentList = studentDAO.filterStudent(2, email);
                if (studentList.isEmpty()) {
                    System.out.println("Không tìm thấy sinh viên nào");
                } else {
                    printTable(studentList);
                }
                break;
            case 3:
                int id = InputValidator.inputInt(sc, "Nhập ID sinh viên cần tìm: ");
                Student student = studentDAO.findById(id);
                if (student == null) {
                    System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
                }
                break;
        }
    }


    private static void printTable(List<Student> studentList) {
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
