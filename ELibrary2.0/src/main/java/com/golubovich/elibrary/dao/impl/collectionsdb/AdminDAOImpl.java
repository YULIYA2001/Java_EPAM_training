package com.golubovich.elibrary.dao.impl.collectionsdb;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.utils.DataSource;

public class AdminDAOImpl implements AdminDAO {

  private final DataSource dataSource = DataSource.getInstance();

  public Admin read() {
    return dataSource.getAdmin();
  }

  public boolean update(Admin updatedAdmin) {
    dataSource.setAdmin(updatedAdmin);
    return true;
  }

  public Admin findByPassword(String password) {
      if (dataSource.getAdmin().getPassword().equals(password)) {
          return dataSource.getAdmin();
      }
    return null;
  }
}

