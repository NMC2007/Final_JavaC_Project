package dao.impl;

import dao.ICourseDAO;
import dao.IDaoCRUD;
import model.Course;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDAOImpl implements IDaoCRUD<Course>, ICourseDAO {

    @Override
    public void insert(Course course) {

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
    public void update(Course course) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement("""
                    UPDATE final_javac_prj_sch.course
                    SET name = ?, duration = ?, instructor = ?, created_at = CURRENT_DATE
                    WHERE id = ?
                """)
        ) {

            ps.setString(1, course.getName());
            ps.setInt(2, course.getDuration());
            ps.setString(3, course.getInstructor());
            ps.setInt(4, course.getId());

            ps.executeUpdate();
            System.out.println("✅ Cập nhật khoá học thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
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

        return null;
    }

    @Override
    public List<Course> findAll() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("SELECT * FROM final_javac_prj_sch.course");
        ) {
            ResultSet rs = pre.executeQuery();

            return addList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<Course> filterByName(String courseName) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("SELECT * FROM final_javac_prj_sch.course WHERE name ILIKE ?");
        ) {
            pre.setString(1, "%" + courseName + "%");
            ResultSet rs = pre.executeQuery();

            return addList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<Course> sort(int sortOption) {
        String sqlCode = "SELECT * FROM final_javac_prj_sch.course";

        switch (sortOption) {
            case 1:
//                id tăng dần
                sqlCode += " ORDER BY id ASC";
                break;
            case 2:
//                id giảm dần
                sqlCode += " ORDER BY id DESC";
                break;
            case 3:
//                tên tăng dần
                sqlCode += " ORDER BY name ASC";
                break;
            case 4:
//                tên giảm dần
                sqlCode += " ORDER BY name DESC";
                break;
            default:
                sqlCode += " ORDER BY id ASC";
        }

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sqlCode);
        ) {

            ResultSet rs = pre.executeQuery();


            return addList(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    private static List<Course> addList(ResultSet rs) throws SQLException {
        List <Course> sortListCourse = new ArrayList<>();

        while (rs.next()) {
            Course course = new Course();

            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDuration(rs.getInt("duration"));
            course.setInstructor(rs.getString("instructor"));
            java.sql.Date sqlDate = rs.getDate("created_at");
            course.setCreatedAt(sqlDate.toLocalDate());

            sortListCourse.add(course);
        }

        return sortListCourse;
    }

}
