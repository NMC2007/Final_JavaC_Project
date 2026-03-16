package business.impl;

import business.CRUDService;
import business.CourseService;
import enums.UpdateStatusEnum;
import utils.tableConfig.CourseTableView;
import dao.impl.CourseManagerDAOImpl;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import model.Course;

import java.util.List;
import java.util.Scanner;

public class CourseServiceImpl implements CRUDService<Course>, CourseService {
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
    public Course findById(int id) {
        return courseDAO.findById(id);
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
    public void updateData(Course course) {
        UpdateStatusEnum result = courseDAO.update(course);
        switch (result) {
            case SUCCESS:
                System.out.println("✅ Cập nhật khoá học thành công!");
                break;
            case ERROR:
                System.out.println("❌ Lỗi! Không thể cập nhật khoá học.");
                break;
        }
    }

    @Override
    public void deleteData(int id) {
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

    @Override
    public void filterCourseByName(String name) {
        List<Course> courseList = courseDAO.filterByName(name);

        if (courseList.isEmpty()) {
            System.out.println("Không tìm thấy khoá học có tên " + name);
        } else {
            System.out.println("✅ Tìm thấy khóa học:");
            CourseTableView.printListCourses(courseList);
        }
    }

    @Override
    public void sortData(int option) {
        List<Course> courseList = courseDAO.sort(option);
        if (courseList.isEmpty()) {
            System.out.println("Danh sách hiện chưa có khoá học nào.");
        } else {
            System.out.println("Danh sách khoá học sau khi sắp xếp:");
            CourseTableView.printListCourses(courseList);
        }
    }
}
