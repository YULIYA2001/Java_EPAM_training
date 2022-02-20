package com.golubovich.elibrary.service;

import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.service.api.GenreService;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.service.api.LibraryService;
import com.golubovich.elibrary.service.impl.AdminServiceImpl;
import com.golubovich.elibrary.service.impl.BookServiceImpl;
import com.golubovich.elibrary.service.impl.ClientServiceImpl;
import com.golubovich.elibrary.service.impl.EdMaterialServiceImpl;
import com.golubovich.elibrary.service.impl.GenreServiceImpl;
import com.golubovich.elibrary.service.impl.LibraryServiceImpl;
import com.golubovich.elibrary.service.impl.MagazineServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final AdminService adminService = new AdminServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final ItemService magazineService = new MagazineServiceImpl();
    private final ItemService bookService = new BookServiceImpl();
    private final ItemService edMaterialService = new EdMaterialServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public AdminService getAdminService() {
        return this.adminService;
    }

    public ClientService getClientService() {
        return this.clientService;
    }

    public ItemService getMagazineService() {
        return this.magazineService;
    }

    public ItemService getBookService() {
        return this.bookService;
    }

    public ItemService getEdMaterialService() {
        return this.edMaterialService;
    }

    public GenreService getGenreService() {
        return this.genreService;
    }

    public LibraryService getLibraryService() {
        return this.libraryService;
    }
}

