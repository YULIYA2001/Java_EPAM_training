package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.NO_CHANGES;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.AdminService;

public class AdminServiceImpl implements AdminService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final AdminDAO adminDAO = this.provider.getAdminDAO();

  public boolean changeData(String surname, String name, String patronymic, String password)
      throws ServiceException {

    if (patronymic == null) {
      patronymic = "";
    }

    Admin admin;
    try {
      admin = adminDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (admin != null) {
      if (!surname.equals(NO_CHANGES)) {
        admin.setSurname(surname);
      }
      if (!name.equals(NO_CHANGES)) {
        admin.setName(name);
      }
      if (!patronymic.equals(NO_CHANGES)) {
        admin.setPatronymic(patronymic);
      }
      if (!password.equals(NO_CHANGES)) {
        admin.setPassword(password);
      }

      try {
        return adminDAO.update(admin);
      } catch (DAOException e) {
        throw new ServiceException(e);
      }
    }

    return false;
  }

  public boolean authorize(String password) throws ServiceException {
    Admin admin;
    try {
      admin = adminDAO.findByPassword(password);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
    return admin != null;
  }

  public String show() throws ServiceException {
    Admin admin;
    try {
      admin = adminDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (admin != null) {
      return admin.toString();
    } else {
      throw new ServiceException("admin == null in class AdminServiceImpl method show()");
    }
  }
}

