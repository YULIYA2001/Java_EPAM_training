package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.DAOException;

public interface AdminDAO {

  Admin read() throws DAOException;

  boolean update(Admin updatedAdmin) throws DAOException;

  Admin findByPassword(String password) throws DAOException;
}
