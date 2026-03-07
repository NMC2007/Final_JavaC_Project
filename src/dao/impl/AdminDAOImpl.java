package dao.impl;

import dao.ILogin;
import model.Admin;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements ILogin<Admin> {
    @Override
    public Admin login(String username, String password) {

        String sql = "SELECT * FROM final_javac_prj_sch.admin WHERE username = ? AND password = ?";

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
