package utils.tableView;

import model.Student;

import java.util.List;

public class StudentTableView {
    public static void printListStudents(List<Student> studentList) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
                "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        for (Student s : studentList) {
            s.displayData();
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    public static void printStudent(Student student) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
                "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        student.displayData();

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
