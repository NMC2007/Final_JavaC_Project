package dao.impl;

import dao.IDaoCRUD;
import dao.ILogin;
import dao.IStudentManagerDAO;
import model.Student;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerDAOImpl implements IDaoCRUD<Student>, IStudentManagerDAO, ILogin<Student> {

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
    public void update(Student student) {
        String sql = """
            UPDATE final_javac_prj_sch.student
            SET name = ?, dob = ?, email = ?, sex = ?, phone = ?, created_at = CURRENT_DATE
            WHERE id = ?
            """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql)
        ) {

            pre.setString(1, student.getName());
            pre.setDate(2, Date.valueOf(student.getDob()));
            pre.setString(3, student.getEmail());
            pre.setBoolean(4, student.isSex());
            pre.setString(5, student.getPhone());
            pre.setInt(6, student.getId());

            pre.executeUpdate();
            System.out.println("✅ Cập nhật sinh viên thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
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

        return null;
    }

    @Override
    public List<Student> findAll() {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(
                        "SELECT * FROM final_javac_prj_sch.student"
                );
        ) {

            ResultSet rs = pre.executeQuery();

            return addList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<Student> sort(int sortOption) {
        String sqlCode = "SELECT * FROM final_javac_prj_sch.student";

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

    @Override
    public Student login(String email, String password) {

        String sql = "SELECT * FROM final_javac_prj_sch.student WHERE email = ? AND password = ?";

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));

                return student;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement("SELECT 1 FROM final_javac_prj_sch.student WHERE email = ?")
        ) {

            pre.setString(1, email);

            ResultSet rs = pre.executeQuery();

            return rs.next(); // true nếu tồn tại

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Student> filterStudent(int option, String Data) {
        String sql = "SELECT * FROM final_javac_prj_sch.student";

        switch (option) {
            case 1:
                sql += " WHERE name LIKE ?";
                break;
            case 2:
                sql += " WHERE email LIKE ?";
                break;
            default:
                sql += " WHERE name LIKE ?";
        }

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql);
        ) {
            pre.setString(1, "%" + Data + "%");
            ResultSet rs = pre.executeQuery();

            return addList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
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
