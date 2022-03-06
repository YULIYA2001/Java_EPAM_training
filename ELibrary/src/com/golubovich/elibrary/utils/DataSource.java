package com.golubovich.elibrary.utils;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.enums.ItemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSource {
    private Library library = new Library("E-Library", "http://elibrary.com", "e-library@gmail.com");
    private Admin admin = new Admin("admin", "admin", "", "admin123");
    private List<Client> clients;
    private List<Genre> genres;
    private Map<ItemType, ArrayList<Item>> items;
    private static final DataSource instance = new DataSource();


    public static DataSource getInstance() {
        return instance;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Map<ItemType, ArrayList<Item>> getItems() {
        return this.items;
    }

    public void setItems(Map<ItemType, ArrayList<Item>> items) {
        this.items = items;
    }
}

