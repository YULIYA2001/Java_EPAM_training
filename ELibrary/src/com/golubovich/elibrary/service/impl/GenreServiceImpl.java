package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.service.api.GenreService;
import java.util.Iterator;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    private final DAOProvider provider = DAOProvider.getInstance();

    public GenreServiceImpl() {
    }

    public boolean add(String[] params) {
        GenreDAO genreDAO = this.provider.getGenreDAO();
        Genre genre = new Genre(params[0], params[1].replace('_', ' '));
        boolean result = genreDAO.create(genre);
        return result;
    }

    public String showAll() {
        GenreDAO genreDAO = this.provider.getGenreDAO();
        List<Genre> genres = genreDAO.readAll();
        if (genres != null && !genres.isEmpty()) {
            StringBuilder response = new StringBuilder();
            Iterator var4 = genres.iterator();

            while(var4.hasNext()) {
                Genre genre = (Genre)var4.next();
                response.append(genre.toReadableString()).append("\n");
            }

            return String.valueOf(response);
        } else {
            return "Список жанров пуст";
        }
    }
}

