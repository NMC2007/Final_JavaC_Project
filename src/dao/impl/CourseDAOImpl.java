package dao.impl;

import dao.ICourseCRUD;
import model.Course;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDAOImpl implements ICourseCRUD<Course> {

    @Override
    public void insert(Course course) {
        List<Course> courseList = new ArrayList<>();

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("INSERT INTO final_javac_prj_sch.course (name, duration, instructor) VALUES (?, ?, ?)");
        ) {

            pre.setString(1, course.getName());
            pre.setInt(2, course.getDuration());
            pre.setString(3, course.getInstructor());

            pre.execute();

            System.out.println("✅ Thêm khoá học thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Scanner sc) {

        Course course = findById(id);

        if (course == null) {
            System.out.println("❌ Không tìm thấy khóa học với id = " + id);
            return;
        }

        System.out.println("✅ Tìm thấy khóa học. Chức năng update đang phát triển...");
    }

    @Override
    public void delete(int id, Scanner sc) {

        Course course = findById(id);

        if (course == null) {
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
                        "DELETE FROM final_javac_prj_sch.course WHERE id = ?"
                );
        ) {

            pre.setInt(1, id);

            int rows = pre.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Xoá khóa học thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course findById(int id) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "SELECT * FROM final_javac_prj_sch.course WHERE id = ?"
                );
        ) {

            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {

                Course course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));

                java.sql.Date sqlDate = rs.getDate("created_at");
                course.setCreatedAt(sqlDate.toLocalDate());

                System.out.println("✅ Tìm thấy khóa học:");

                System.out.println("----------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-25s | %-10s | %-20s | %-12s |\n",
                        "ID", "NAME", "DURATION", "INSTRUCTOR", "CREATED");
                System.out.println("----------------------------------------------------------------------------------------");
                course.displayData();
                System.out.println("----------------------------------------------------------------------------------------");



                return course;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("❌ Không tìm thấy khóa học với id = " + id);
        return null;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("SELECT * FROM final_javac_prj_sch.course");
        ) {
            ResultSet rs = pre.executeQuery();

            System.out.println(rs);

            while (rs.next()) {
                Course course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                java.sql.Date sqlDate = rs.getDate("created_at");
                course.setCreatedAt(sqlDate.toLocalDate());

                courseList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }
}
