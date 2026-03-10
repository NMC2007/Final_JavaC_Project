package model;

import model.IBaseModel.IEnrollmentView;

import java.time.LocalDate;

public class StudentStatus implements IEnrollmentView {
    private int id;
    private String name;
    private String email;
    private boolean sex;
    private LocalDate registeredAt;
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentStatus() {
    }

    public StudentStatus(int id, String name, String email, boolean sex, LocalDate registeredAt, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.registeredAt = registeredAt;
        Status = status;
    }


    @Override
    public void display() {
        System.out.printf("| %-4d | %-20s | %-25s | %-5s | %-12s | %-10s |\n",
                id,
                name,
                email,
                sex ? "Nam" : "Nữ",
                registeredAt,
                Status
        );
    }
}
