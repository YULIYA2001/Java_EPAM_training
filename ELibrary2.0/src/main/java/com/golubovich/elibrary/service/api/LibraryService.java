package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.service.ServiceException;

public interface LibraryService {

  String showInfo() throws ServiceException;

  boolean changeInfo(String[] updatedFields) throws ServiceException;
}
