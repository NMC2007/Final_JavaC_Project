package dao.impl;

import dao.EnrollmentManagerDAO;
import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import model.Enrollment;
import model.Student;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentManagerDAOImpl implements EnrollmentManagerDAO {
    @Override
    public List<Student> getStudentsBelongToCourse(int courseId) {

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        """
                            SELECT s.*
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

    private List<Student> addList(ResultSet rs) throws SQLException {
        List<Student> studentList = new ArrayList<>();

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

        return studentList;
    }
}
