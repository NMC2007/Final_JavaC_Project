package model;

import model.IBaseModel.IDisplayData;

public class StatisticalCourse implements IDisplayData {
    private int idCourse;
    private String courseName;
    private int totalStudent;

    public StatisticalCourse() {
    }

    public StatisticalCourse(int idCourse, String courseName, int totalStudent) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.totalStudent = totalStudent;
    }


    @Override
    public void displayData() {
        System.out.printf("| %-8d | %-35s | %-13d |\n", idCourse, courseName, totalStudent);
    }
}
