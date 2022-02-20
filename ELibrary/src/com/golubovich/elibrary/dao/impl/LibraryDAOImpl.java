package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.source.DataSource;

public class LibraryDAOImpl implements LibraryDAO {
    private final DataSource dataSource = DataSource.getInstance();

    public LibraryDAOImpl() {
    }

    public boolean changeData(String name, String urlAddress, String eMail) {
        if (!name.equals("-")) {
            this.dataSource.getLibrary().setName(name);
        }

        if (!urlAddress.equals("-")) {
            this.dataSource.getLibrary().setUrlAddress(urlAddress);
        }

        if (!eMail.equals("-")) {
            this.dataSource.getLibrary().setEMail(eMail);
        }

        return true;
    }

    public Library read() {
        return this.dataSource.getLibrary();
    }
}

