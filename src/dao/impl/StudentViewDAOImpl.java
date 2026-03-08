package dao.impl;

import dao.IStudentViewDAO;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Course;
import model.Enrollment;
import model.EnrollmentView;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentViewDAOImpl implements IStudentViewDAO {
    @Override
    public List<Course> showListCourse() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("SELECT * FROM final_javac_prj_sch.course");
        ) {
            ResultSet rs = pre.executeQuery();

            return addListCourse(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<EnrollmentView> showListEnrollments(int idStudent) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("""
                    SELECT c.name, e.registered_at, e.status
                    FROM final_javac_prj_sch.enrollment e
                    JOIN final_javac_prj_sch.course c ON e.course_id = c.id
                    WHERE e.student_id = ?
                    """);
        ) {
            pre.setInt(1, idStudent);
            ResultSet rs = pre.executeQuery();

            return addListEnrollment(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public InsertStatusEnum createEnrollment(Enrollment enrollment) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("INSERT INTO final_javac_prj_sch.enrollment (student_id, course_id) VALUES (?, ?)");
        ) {

            pre.setInt(1, enrollment.getStudentId());
            pre.setInt(2, enrollment.getCourseId());

            int row = pre.executeUpdate();
            if (row > 0) {
                return InsertStatusEnum.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return InsertStatusEnum.ERROR;
    }

    @Override
    public DeleteStatusEnum deleteEnrollment(int idStudent, int idCourse) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement check = conn.prepareStatement(
                        "SELECT status FROM final_javac_prj_sch.enrollment WHERE student_id = ? AND course_id = ?"
                );
        ) {

            check.setInt(1, idStudent);
            check.setInt(2, idCourse);

            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
//                ❌ Bạn chưa đăng ký khóa học này.
                return DeleteStatusEnum.DOSE_NOT_EXIST;
            }

            String status = rs.getString("status");

            if (!"WAITING".equalsIgnoreCase(status)) {
//                ❌ Khóa học đã được duyệt, không thể huỷ.
                return DeleteStatusEnum.UNAUTHORIZED;
            }

            try (
                    PreparedStatement delete = conn.prepareStatement(
                            "DELETE FROM final_javac_prj_sch.enrollment WHERE student_id = ? AND course_id = ?"
                    );
            ) {

                delete.setInt(1, idStudent);
                delete.setInt(2, idCourse);

                int rows = delete.executeUpdate();

                if (rows > 0) {
//                    ✅ Huỷ đăng ký khóa học thành công.
                    return DeleteStatusEnum.SUCCESS;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        ❌ Lỗi không huỷ được.
        return DeleteStatusEnum.ERROR;
    }

    @Override
    public boolean checkedPassword(int idStudent, String oldPassword) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "SELECT password FROM final_javac_prj_sch.student WHERE id = ?"
                );
        ) {

            pre.setInt(1, idStudent);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return dbPassword.equals(oldPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UpdateStatusEnum updatePassword(int idStudent, String password) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "UPDATE final_javac_prj_sch.student SET password = ? WHERE id = ?"
                );
        ) {

            pre.setString(1, password);
            pre.setInt(2, idStudent);

            int rows = pre.executeUpdate();

            if (rows > 0) {
                return UpdateStatusEnum.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UpdateStatusEnum.ERROR;
    }


    private static List<EnrollmentView> addListEnrollment(ResultSet rs) throws SQLException {
        List<EnrollmentView> enrollments = new ArrayList<>();
        while (rs.next()) {
            EnrollmentView view = new EnrollmentView(
                    rs.getString("name"),
                    rs.getTimestamp("registered_at").toLocalDateTime(),
                    rs.getString("status")
            );

            enrollments.add(view);
        }

        return enrollments;
    }

    private static List<Course> addListCourse(ResultSet rs) throws SQLException {
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
