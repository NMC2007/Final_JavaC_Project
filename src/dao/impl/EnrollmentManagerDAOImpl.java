package dao.impl;

import dao.EnrollmentManagerDAO;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;
import model.Enrollment;
import model.StudentStatus;
import utils.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentManagerDAOImpl implements EnrollmentManagerDAO {
    @Override
    public List<StudentStatus> getStudentsBelongToCourse(int courseId) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        """
                                SELECT
                                s.id,
                                s.name,
                                s.email,
                                s.sex,
                                e.registered_at AS registeredAt,
                                e.status AS status
                                FROM final_javac_prj_sch.student s
                                JOIN final_javac_prj_sch.enrollment e
                                ON s.id = e.student_id
                                WHERE e.course_id = ?
                            """
                );
        ) {

            pre.setInt(1, courseId);

            ResultSet rs = pre.executeQuery();

            return addList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public InsertStatusEnum addStudentToTheCourse(Enrollment enrollment) {
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
    public DeleteStatusEnum deleteStudentFromTheCourse(int idStudent, int idCourse) {
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
    public UpdateStatusEnum updateStatusStudent(int idStudent, int idCourse, String status) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        """
                        UPDATE final_javac_prj_sch.enrollment
                        SET status = ?
                        WHERE student_id = ? AND course_id = ?
                        """
                );
        ) {

            pre.setString(1, status);
            pre.setInt(2, idStudent);
            pre.setInt(3, idCourse);

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                return UpdateStatusEnum.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UpdateStatusEnum.ERROR;
    }

    private List<StudentStatus> addList(ResultSet rs) throws SQLException {
        List<StudentStatus> studentStatusList = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            boolean sex = rs.getBoolean("sex");

            LocalDate registeredAt = rs.getDate("registeredAt").toLocalDate();
            String status = rs.getString("status");

            StudentStatus studentStatus =
                    new StudentStatus(id, name, email, sex, registeredAt, status);

            studentStatusList.add(studentStatus);
        }

        return studentStatusList;
    }
}
