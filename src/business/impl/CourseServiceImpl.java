package business.impl;

import business.CourseService;
import utils.tableView.CourseTableView;
import dao.impl.CourseManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Course;
import validation.InputValidator;

import java.util.List;
import java.util.Scanner;

public class CourseServiceImpl implements CourseService {
    private static final CourseManagerDAOImpl courseDAO = new CourseManagerDAOImpl();

    @Override
    public void showData() {
        List<Course> courseList = courseDAO.findAll();
        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào.");
        } else {
            CourseTableView.printListCourses(courseList);
        }
    }

    @Override
    public void createData(Scanner sc) {
        Course newCourse = new Course();

        newCourse.inputData(sc);

        InsertStatusEnum result = courseDAO.insert(newCourse);

        switch (result) {
            case SUCCESS:
                System.out.println("✅ Thêm khoá học thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể thêm khóa học.");
                break;
        }
    }

    @Override
    public void updateData(Scanner sc) {
        int id = InputValidator.inputInt(sc, "Nhập ID khoá học cần sửa: ");

        Course course = courseDAO.findById(id);
        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);
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
                        UpdateStatusEnum result = courseDAO.update(course);
                        switch (result) {
                            case SUCCESS:
                                System.out.println("✅ Cập nhật khoá học thành công!");
                                break;
                            case ERROR:
                                System.out.println("❌ Lỗi! Không thể cập nhật khoá học.");
                                break;
                        }
                        return;
                    case 5:
                        System.out.println("❌ Hủy cập nhật.");
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
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printCourse(course);

            String confirm = InputValidator.inputString(sc, "Để xác nhận xoá hãy nhập y: ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("❌ Đã huỷ xoá.");
            } else {
                DeleteStatusEnum result = courseDAO.delete(id);
                switch (result) {
                    case SUCCESS:
                        System.out.println("✅ Xoá khóa học thành công!");
                        break;
                    case ERROR:
                        System.out.println("❌ Lỗi! Không thể xoá khoá học.");
                        break;
                }
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
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printListCourses(courseList);
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
            System.out.println("Danh sách hiện chưa có khoá học nào.");
        } else {
            System.out.println("Danh sách khoá học sau khi sắp xếp:");
            CourseTableView.printListCourses(courseList);
        }
    }
}
