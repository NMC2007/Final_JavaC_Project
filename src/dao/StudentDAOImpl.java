package dao;

import dao.impl.ICourseCRUD;
import model.Student;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAOImpl implements ICourseCRUD<Student> {

    @Override
    public void insert(Student student) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "INSERT INTO final_javac_prj_sch.student (name, dob, email, sex, phone, password) VALUES (?, ?, ?, ?, ?, ?)"
                );
        ) {

            pre.setString(1, student.getName());
            pre.setDate(2, Date.valueOf(student.getDob()));
            pre.setString(3, student.getEmail());
            pre.setBoolean(4, student.isSex());
            pre.setString(5, student.getPhone());
            pre.setString(6, student.getPassword());

            pre.execute();

            System.out.println("✅ Thêm sinh viên thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Scanner sc) {

        Student student = findById(id);

        if (student == null) {
            System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
            return;
        }

        System.out.println("✅ Tìm thấy sinh viên. Chức năng update đang phát triển...");
    }

    @Override
    public void delete(int id, Scanner sc) {

        Student student = findById(id);

        if (student == null) {
            return;
        }

        System.out.print("Nhập y nếu chắc chắn muốn xoá: ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("❌ Đã huỷ xoá.");
            return;
        }

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "DELETE FROM final_javac_prj_sch.student WHERE id = ?"
                );
        ) {

            pre.setInt(1, id);

            int rows = pre.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Xoá sinh viên thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "SELECT * FROM final_javac_prj_sch.student WHERE id = ?"
                );
        ) {

            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));

                Date dob = rs.getDate("dob");
                student.setDob(dob.toLocalDate());

                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));

                Date created = rs.getDate("created_at");
                student.setCreatedAt(created.toLocalDate());

                System.out.println("✅ Tìm thấy sinh viên:");

                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-6s | %-15s | %-12s |\n",
                        "ID", "NAME", "DOB", "EMAIL", "SEX", "PHONE", "CREATED");
                System.out.println("--------------------------------------------------------------------------------------------------------------------");

                student.displayData();

                System.out.println("--------------------------------------------------------------------------------------------------------------------");

                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("❌ Không tìm thấy sinh viên với id = " + id);
        return null;
    }

    @Override
    public List<Student> findAll() {

        List<Student> studentList = new ArrayList<>();

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "SELECT * FROM final_javac_prj_sch.student"
                );
        ) {

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));

                Date dob = rs.getDate("dob");
                student.setDob(dob.toLocalDate());

                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));

                Date created = rs.getDate("created_at");
                student.setCreatedAt(created.toLocalDate());

                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
