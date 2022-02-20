package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.exceptions.DAOException;
import com.golubovich.elibrary.exceptions.ServiceException;
import com.golubovich.elibrary.service.api.AdminService;

public class AdminServiceImpl implements AdminService {
    private final DAOProvider provider = DAOProvider.getInstance();

    public AdminServiceImpl() {
    }

    public boolean changeData(String surname, String name, String patronymic, String password) {
        if (patronymic == null) {
            patronymic = "";
        }

        AdminDAO adminDAO = this.provider.getAdminDAO();
        boolean result = adminDAO.update(surname, name, patronymic, password);
        return result;
    }

    public boolean authorize(String password) throws ServiceException {
        AdminDAO adminDAO = this.provider.getAdminDAO();

        try {
            boolean result = adminDAO.authorization(password);
            return result;
        } catch (DAOException var5) {
            throw new ServiceException(var5);
        }
    }

    public String show() {
        AdminDAO adminDAO = this.provider.getAdminDAO();
        Admin admin = adminDAO.read();
        return admin.toReadableString();
    }
}

