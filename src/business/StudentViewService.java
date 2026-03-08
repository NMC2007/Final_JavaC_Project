package business;

import java.util.Scanner;

public interface StudentViewService {
    void showCourse();
    void showEnrollment(int idStudent);
    void createEnrollment(Scanner sc, int idStudent);
    void deleteEnrollment(Scanner sc, int idStudent);
    void updatePassword(Scanner sc, int idStudent);
}
