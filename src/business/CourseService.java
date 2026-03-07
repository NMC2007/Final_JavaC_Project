package business;

import model.Course;

import java.util.Scanner;

public interface CourseService extends CRUDService {
    void filterCourseByName(Scanner sc);
}
