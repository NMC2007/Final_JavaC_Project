package utils.tableView;

import model.Course;

import java.util.List;

public class CourseTableView {
    public static void printListCourses(List<Course> courseList) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-25s | %-10s | %-20s | %-12s |\n",
                "ID", "NAME", "DURATION", "INSTRUCTOR", "CREATED");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Course c : courseList) {
            c.displayData();
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    public static void printCourse(Course course) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-25s | %-10s | %-20s | %-12s |\n",
                "ID", "NAME", "DURATION", "INSTRUCTOR", "CREATED");
        System.out.println("----------------------------------------------------------------------------------------");
        course.displayData();
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
