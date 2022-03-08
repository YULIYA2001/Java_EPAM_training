package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.NO_CHANGES;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.LibraryService;

public class LibraryServiceImpl implements LibraryService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final LibraryDAO libraryDAO = provider.getLibraryDAO();

  public String showInfo() throws ServiceException {
    Library library;
    try {
      library = libraryDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (library != null) {
      return library.toString();
    }
    else {
      throw new ServiceException("library == null in class  LibraryServiceImpl method showInfo()");
    }
  }

  public boolean changeInfo(String[] updatedFields) throws ServiceException {
    String name = updatedFields[0];
    String urlAddress = updatedFields[1];
    String eMail = updatedFields[2];

    try {
      Library library = libraryDAO.read();

      if (library != null) {
        if (!name.equals(NO_CHANGES)) {
          library.setName(name.replace('_', ' '));
        }
        if (!urlAddress.equals(NO_CHANGES)) {
          library.setUrlAddress(urlAddress);
        }
        if (!eMail.equals(NO_CHANGES)) {
          library.setEMail(eMail);
        }
        libraryDAO.update(library);
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }
}
