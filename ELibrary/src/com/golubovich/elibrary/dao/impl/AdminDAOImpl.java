package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.exceptions.DAOException;
import com.golubovich.elibrary.source.DataSource;

public class AdminDAOImpl implements AdminDAO {
    private final DataSource dataSource = DataSource.getInstance();

    public AdminDAOImpl() {
    }

    public boolean update(String surname, String name, String patronymic, String password) {
        if (!surname.equals("-")) {
            this.dataSource.getAdmin().setSurname(surname);
        }

        if (!name.equals("-")) {
            this.dataSource.getAdmin().setName(name);
        }

        if (!patronymic.equals("-")) {
            this.dataSource.getAdmin().setPatronymic(patronymic);
        }

        if (!password.equals("-")) {
            this.dataSource.getAdmin().setPassword(password);
        }

        return true;
    }

    public boolean authorization(String password) throws DAOException {
        return this.dataSource.getAdmin().getPassword().equals(password);
    }

    public Admin read() {
        return this.dataSource.getAdmin();
    }
}

