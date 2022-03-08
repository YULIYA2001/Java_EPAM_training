package com.golubovich.elibrary.service.api;


import com.golubovich.elibrary.service.ServiceException;

public interface AdminService {

  String show() throws ServiceException;

  boolean changeData(String surname, String name, String patronymic, String password)
      throws ServiceException;

  boolean authorize(String password) throws ServiceException;
}
