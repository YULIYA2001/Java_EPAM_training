package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.service.api.AdminService;

public class AdminServiceImpl implements AdminService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private  final AdminDAO adminDAO = this.provider.getAdminDAO();

    public boolean changeData(String surname, String name, String patronymic, String password) {
        if (patronymic == null) {
            patronymic = "";
        }

        Admin admin = adminDAO.read();

        if (admin != null) {
            if (!surname.equals("-")){
                admin.setSurname(surname);
            }
            if (!name.equals("-")) {
                admin.setName(name);
            }
            if (!patronymic.equals("-")) {
                admin.setPatronymic(patronymic);
            }
            if (!password.equals("-")) {
                admin.setPassword(password);
            }
            return adminDAO.update(admin);
        }

        return false;
    }

    public boolean authorize(String password) {
        Admin admin = adminDAO.findByPassword(password);
        return admin != null;
    }

    public String show() {
        Admin admin = adminDAO.read();
        if (admin != null) {
            return admin.toString();
        }
        else {
            return "1 error in class AdminServiceImpl method show";
        }
    }
}

