package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Admin;

public interface AdminDAO {
    Admin read();
    boolean update(Admin updatedAdmin);// throws DAOException;

    Admin findByPassword(String password);// throws DAOException;
}
