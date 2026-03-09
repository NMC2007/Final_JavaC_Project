package model;

import model.IBaseModel.IEnrollmentView;

import java.time.LocalDateTime;

public class EnrollmentView implements IEnrollmentView {
    private int idCourse;
    private String courseName;
    private LocalDateTime registeredAt;
    private String status;

//    public EnrollmentView(String courseName, LocalDateTime registeredAt, String status) {
//        this.courseName = courseName;
//        this.registeredAt = registeredAt;
//        this.status = status;
//    }

    public EnrollmentView() {
    }

    public EnrollmentView(int idCourse, String courseName, LocalDateTime registeredAt, String status) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    @Override
    public void display() {
        System.out.printf("| %-12s | %-25s | %-35s | %-10s |\n",
                idCourse,
                courseName,
                registeredAt,
                status
        );
    }
}
