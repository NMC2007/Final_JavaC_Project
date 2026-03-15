package model;

import model.IBaseModel.IDisplayData;

public class StatisticalCourseAndStudent implements IDisplayData {
    private int totalStudent;
    private int totalCourse;

    public StatisticalCourseAndStudent() {
    }

    public StatisticalCourseAndStudent(int totalStudent, int totalCourse) {
        this.totalStudent = totalStudent;
        this.totalCourse = totalCourse;
    }

    @Override
    public void displayData() {
        System.out.println("+---------------+---------------+");
        System.out.printf("| %-13s | %-13s |\n", "Total Student", "Total Course");
        System.out.println("+---------------+---------------+");
        System.out.printf("| %-13d | %-13d |\n", totalStudent, totalCourse);
        System.out.println("+---------------+---------------+");
    }
}
