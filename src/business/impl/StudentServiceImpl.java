package business.impl;

import business.CRUDService;
import business.StudentService;
import utils.tableConfig.StudentTableView;
import dao.impl.StudentManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Student;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImpl implements CRUDService<Student>, StudentService {
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
    public void updateData(Student student) {
        UpdateStatusEnum result = studentDAO.update(student);
        switch (result) {
            case SUCCESS:
                System.out.println("✅ Cập nhật sinh viên thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể cập nhật dữ liệu sinh viên.");
                break;
        }
    }

    @Override
    public void deleteData(int id) {
        DeleteStatusEnum result = studentDAO.delete(id);
        switch (result) {
            case SUCCESS:
                System.out.println("✅ Xoá sinh viên thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể xoá sinh viên.");
        }
    }

    @Override
    public void sortData(int option) {
        List<Student> StudentList = studentDAO.sort(option);

        if (StudentList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có sinh viên nào.");
        } else {
            System.out.println("Danh sách sinh viên sau khi sắp xếp:");;
            StudentTableView.printListStudents(StudentList);
        }
    }

    @Override
    public void filterStudent(int option, String data) {
        if (option != 3) {
            List<Student> studentList = studentDAO.filterStudent(option, data);
            if (studentList.isEmpty()) {
                System.out.println("Không tìm thấy sinh viên nào.");
            } else {
                System.out.println("✅ Tìm thấy sinh viên:");
                StudentTableView.printListStudents(studentList);
            }
        }
    }

    @Override
    public void filterStudent(int id) {
        Student student = studentDAO.findById(id);
        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy sinh viên:");
            StudentTableView.printStudent(student);
        }
    }
}
