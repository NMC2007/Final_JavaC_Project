package model;

import model.IBaseModel.IEnrollmentView;

import java.time.LocalDateTime;

public class EnrollmentView implements IEnrollmentView {
    private String courseName;
    private LocalDateTime registeredAt;
    private String status;

    public EnrollmentView(String courseName, LocalDateTime registeredAt, String status) {
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    @Override
    public void display() {
        System.out.printf("| %-25s | %-35s | %-10s |\n",
                courseName,
                registeredAt,
                status
        );
    }
}
