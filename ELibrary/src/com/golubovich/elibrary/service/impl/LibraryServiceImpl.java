package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.service.api.LibraryService;

public class LibraryServiceImpl implements LibraryService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final LibraryDAO libraryDAO = provider.getLibraryDAO();

    public String showInfo() {
        Library library = libraryDAO.read();
        return library == null ? "1 error LibraryServiceImpl showInfo" : library.toString();
    }

    public boolean changeInfo(String[] updatedFields) {
        String name = updatedFields[0];
        String urlAddress = updatedFields[1];
        String eMail = updatedFields[2];

        Library library = libraryDAO.read();

        if(library != null) {
            if (!name.equals("-")) {
                library.setName(name);
            }
            if (!urlAddress.equals("-")) {
                library.setUrlAddress(urlAddress);
            }
            if (!eMail.equals("-")) {
                library.setEMail(eMail);
            }
            libraryDAO.update(library);
            return true;
        }
        return false;
    }
}
