package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.ServiceException;

public interface ClientService {

  boolean register(String[] orderedFields) throws ServiceException;

  String showAll() throws ServiceException;

  boolean changeData(String eMail, String surname, String name, String patronymic,
      String password, ClientStatus clientStatus) throws ServiceException;

  boolean deleteByEMail(String var1) throws ServiceException;

  boolean authorize(String eMail, String password) throws ServiceException;

  String showByEMail(String eMail) throws ServiceException;
}
