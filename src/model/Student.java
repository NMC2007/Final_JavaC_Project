package model;

import model.IBaseModel.IBaseModel;
import validation.InputValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class Student implements IBaseModel {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private boolean sex;
    private String phone;
    private String password;
    private LocalDate createdAt;

    public Student() {
    }

    public Student(int id, String name, LocalDate dob, String email, boolean sex, String phone, String password, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


    //    override
    @Override
    public void inputData(Scanner sc) {
        this.name = InputValidator.inputString(sc, "Nhập tên sinh viên: ");

        this.dob = InputValidator.inputDate(sc, "Nhập ngày sinh (dd/MM/yyyy): ");

        this.email = InputValidator.inputEmail(sc, "Nhập email: ");

        int choiceSex = InputValidator.inputMenu(sc,
                "Chọn giới tính:\n" +
                        "1. Nam\n" +
                        "2. Nữ\n" +
                        "Lựa chọn của bạn: ",  2);
        this.sex = (choiceSex == 1);

        this.phone = InputValidator.inputPhone(sc, "Nhập số điện thoại: ");

        this.password = InputValidator.inputString(sc, "Nhập mật khẩu: ");
    }

    @Override
    public void displayData() {
        System.out.printf("| %-5d | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
                id,
                name,
                dob,
                email,
                sex ? "Nam" : "Nữ",
                phone,
                createdAt
        );
    }
}
