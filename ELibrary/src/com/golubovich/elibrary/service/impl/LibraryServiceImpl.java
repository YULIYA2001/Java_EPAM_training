package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.service.api.LibraryService;

public class LibraryServiceImpl implements LibraryService {
    private final DAOProvider provider = DAOProvider.getInstance();

    public LibraryServiceImpl() {
    }

    public String showInfo() {
        LibraryDAO libraryDAO = this.provider.getLibraryDAO();
        Library library = libraryDAO.read();
        return library == null ? "1 error LibraryServiceImpl showInfo" : library.toReadableString();
    }

    public boolean changeInfo(String[] params) {
        LibraryDAO libraryDAO = this.provider.getLibraryDAO();
        boolean result = libraryDAO.changeData(params[0], params[1], params[2]);
        return result;
    }
}
