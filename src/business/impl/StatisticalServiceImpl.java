package business.impl;

import business.StatisticalService;
import dao.impl.StatisticalDAOImpl;
import model.StatisticalCourse;
import model.StatisticalCourseAndStudent;
import utils.tableConfig.StatisticalTableView;

import java.util.List;

public class StatisticalServiceImpl implements StatisticalService {
    private static final StatisticalDAOImpl statisticalDAO = new StatisticalDAOImpl();
    @Override
    public void getTotalCoursesAndStudents() {
        StatisticalCourseAndStudent data = statisticalDAO.getTotalCoursesAndStudents();
        if (data != null) {
            System.out.println("Số lượng khoá học và sinh viên:");
            data.displayData();
        }
    }

    @Override
    public void getStudentCountByCourse() {
        List<StatisticalCourse> dataList = statisticalDAO.getStudentCountByCourse();
        if (dataList.isEmpty()) {
            System.out.println("Danh sách rỗng.");
        } else {
            System.out.println("Số lượng sinh viên trên từng khoá học:");
            StatisticalTableView.printLisStatisticalCourse(dataList);
        }
    }

    @Override
    public void getTop5CoursesByStudentCount() {
        List<StatisticalCourse> dataList = statisticalDAO.getTop5CoursesByStudentCount();
        if (dataList.isEmpty()) {
            System.out.println("Danh sách rỗng.");
        } else {
            System.out.println("Top 5 khoá học nhiều sinh viên nhất:");
            StatisticalTableView.printLisStatisticalCourse(dataList);
        }
    }

    @Override
    public void getCoursesWithMoreThan10Students() {
        List<StatisticalCourse> dataList = statisticalDAO.getCoursesWithMoreThan10Students();
        if (dataList.isEmpty()) {
            System.out.println("Danh sách rỗng.");
        } else {
            System.out.println("Các khoá học có trên 10 sinh viên:");
            StatisticalTableView.printLisStatisticalCourse(dataList);
        }
    }
}
