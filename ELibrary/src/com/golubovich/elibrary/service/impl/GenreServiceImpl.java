package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.service.api.GenreService;
import java.util.Collections;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final GenreDAO genreDAO = provider.getGenreDAO();

    public boolean add(String name, String description) {
        Genre genre = new Genre(name, description.replace('_', ' '));
        return genreDAO.create(genre);
    }

    public String showAll() {
        List<Genre> genres = genreDAO.read();

        if (genres != null && !genres.isEmpty()) {
            Collections.sort(genres);
            return genresListToString(genres);
        }
        return "Список жанров пуст";
    }

    private String genresListToString(List<Genre> genres) {
        StringBuilder genresString = new StringBuilder();

        for (Genre genre : genres) {
            genresString.append(genre.toString()).append("\n");
        }
        return String.valueOf(genresString);
    }
}

