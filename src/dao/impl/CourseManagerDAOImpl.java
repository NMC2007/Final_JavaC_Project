package dao.impl;

import dao.ICourseManagerDAO;
import dao.IDaoCRUD;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Course;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManagerDAOImpl implements IDaoCRUD<Course>, ICourseManagerDAO {

    @Override
    public InsertStatusEnum insert(Course course) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("INSERT INTO final_javac_prj_sch.course (name, duration, instructor) VALUES (?, ?, ?)");
        ) {

            pre.setString(1, course.getName());
            pre.setInt(2, course.getDuration());
            pre.setString(3, course.getInstructor());

            int rows = pre.executeUpdate();

            if (rows > 0) {
                return InsertStatusEnum.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return InsertStatusEnum.ERROR;
    }

    @Override
    public UpdateStatusEnum update(Course course) {

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

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return UpdateStatusEnum.SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdateStatusEnum.ERROR;
    }


    @Override
    public DeleteStatusEnum delete(int id) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "DELETE FROM final_javac_prj_sch.course WHERE id = ?"
                );
        ) {

            pre.setInt(1, id);

            int rows = pre.executeUpdate();

            if (rows > 0) {
                return  DeleteStatusEnum.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DeleteStatusEnum.ERROR;
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
