package com.golubovich.elibrary.dao;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.api.AdminDAO;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.dao.impl.AdminDAOImpl;
import com.golubovich.elibrary.dao.impl.BookDAOImpl;
import com.golubovich.elibrary.dao.impl.ClientDAOImpl;
import com.golubovich.elibrary.dao.impl.EducationalMaterialDAOImpl;
import com.golubovich.elibrary.dao.impl.GenreDAOImpl;
import com.golubovich.elibrary.dao.impl.LibraryDAOImpl;
import com.golubovich.elibrary.dao.impl.MagazineDAOImpl;

public final class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final ClientDAO clientDAO = new ClientDAOImpl();
    private final ItemDAO<Magazine> magazineDAO = new MagazineDAOImpl();
    private final ItemDAO<Book> bookDAO = new BookDAOImpl();
    private final ItemDAO<EducationalMaterial> edMaterialDAO = new EducationalMaterialDAOImpl();
    private final GenreDAO genreDAO = new GenreDAOImpl();
    private final LibraryDAO libraryDAO = new LibraryDAOImpl();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public ClientDAO getClientDAO() {
        return this.clientDAO;
    }

    public AdminDAO getAdminDAO() {
        return this.adminDAO;
    }

    public ItemDAO<Magazine> getMagazineDAO() {
        return this.magazineDAO;
    }

    public GenreDAO getGenreDAO() {
        return this.genreDAO;
    }

    public ItemDAO<Book> getBookDAO() {
        return this.bookDAO;
    }

    public ItemDAO<EducationalMaterial> getEdMaterialDAO() {
        return this.edMaterialDAO;
    }

    public LibraryDAO getLibraryDAO() {
        return this.libraryDAO;
    }
}

