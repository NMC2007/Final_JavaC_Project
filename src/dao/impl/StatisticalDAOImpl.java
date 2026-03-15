package dao.impl;

import dao.IStatisticalDAO;
import model.StatisticalCourse;
import model.StatisticalCourseAndStudent;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticalDAOImpl implements IStatisticalDAO {
    @Override
    public StatisticalCourseAndStudent getTotalCoursesAndStudents() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("""
                    SELECT
                    (SELECT COUNT(*) FROM final_javac_prj_sch.student) AS total_students,
                    (SELECT COUNT(*) FROM final_javac_prj_sch.course) AS total_courses;
                """);
        ) {
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return new StatisticalCourseAndStudent(
                        rs.getInt("total_students"),
                        rs.getInt("total_courses")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StatisticalCourse> getStudentCountByCourse() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("""
                    SELECT
                        c.id AS course_id,
                        c.name AS course_name,
                        COUNT(e.student_id) AS total_students
                    FROM final_javac_prj_sch.course c
                    LEFT JOIN final_javac_prj_sch.enrollment e ON c.id = e.course_id
                    GROUP BY c.id, c.name
                    ORDER BY c.id;
                """);
        ) {
            ResultSet rs = pre.executeQuery();

            return addListStatisticalCourse(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<StatisticalCourse> getTop5CoursesByStudentCount() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("""
                    SELECT
                        c.id AS course_id,
                        c.name AS course_name,
                        COUNT(e.student_id) AS total_students
                    FROM final_javac_prj_sch.course c
                    LEFT JOIN final_javac_prj_sch.enrollment e ON c.id = e.course_id
                    GROUP BY c.id, c.name
                    ORDER BY total_students DESC
                    LIMIT 5;
                """);
        ) {
            ResultSet rs = pre.executeQuery();

            return addListStatisticalCourse(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<StatisticalCourse> getCoursesWithMoreThan10Students() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("""
                    SELECT
                        c.id AS course_id,
                        c.name AS course_name,
                        COUNT(e.student_id) AS total_students
                    FROM final_javac_prj_sch.course c
                    JOIN final_javac_prj_sch.enrollment e ON c.id = e.course_id
                    GROUP BY c.id, c.name
                    HAVING COUNT(e.student_id) > 10
                    ORDER BY total_students DESC;
                """);
        ) {
            ResultSet rs = pre.executeQuery();

            return addListStatisticalCourse(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<StatisticalCourse> addListStatisticalCourse(ResultSet rs) throws SQLException {
        List<StatisticalCourse> list = new ArrayList<>();
        while (rs.next()) {
            StatisticalCourse data = new StatisticalCourse (
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getInt("total_students")
            );
            list.add(data);
        }
        return list;
    }

}
