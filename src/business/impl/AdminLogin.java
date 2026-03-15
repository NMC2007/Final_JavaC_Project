package business.impl;

import business.ILoginBusiness;
import dao.impl.AdminDAOImpl;
import model.Admin;

public class AdminLogin implements ILoginBusiness<Admin> {
    private static final AdminDAOImpl adminDAO = new AdminDAOImpl();
    @Override
    public Admin CheckLogin(String username, String password) {
        return adminDAO.login(username, password);
    }
}
