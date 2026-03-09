package business;

import java.util.Scanner;

public interface EnrollmentService {
    void showListStudentsBelongToCourse(Scanner sc);
    void addStudentToTheCourse(Scanner sc);
    void deleteStudentFromTheCourse(Scanner sc);
}
