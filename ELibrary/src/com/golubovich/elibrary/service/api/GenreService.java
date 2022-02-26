package com.golubovich.elibrary.service.api;

public interface GenreService {
    boolean add(String name, String description);
    String showAll();
}

