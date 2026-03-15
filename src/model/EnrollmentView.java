package model;

import model.IBaseModel.IDisplayData;

import java.time.LocalDateTime;

public class EnrollmentView implements IDisplayData {
    private int idCourse;
    private String courseName;
    private LocalDateTime registeredAt;
    private String status;

    public EnrollmentView() {
    }

    public EnrollmentView(int idCourse, String courseName, LocalDateTime registeredAt, String status) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    @Override
    public void displayData() {
        System.out.printf("| %-12s | %-25s | %-35s | %-10s |\n",
                idCourse,
                courseName,
                registeredAt,
                status
        );
    }
}
