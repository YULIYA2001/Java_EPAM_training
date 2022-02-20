package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.source.DataSource;
import java.util.Iterator;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {
    private final DataSource dataSource = DataSource.getInstance();

    public GenreDAOImpl() {
    }

    public boolean create(Genre genre) {
        boolean result = this.dataSource.getGenres().add(genre);
        return result;
    }

    public List<Genre> readAll() {
        return this.dataSource.getGenres();
    }

    public Genre read(int code) {
        Iterator iterator = this.dataSource.getGenres().iterator();

        Genre genre;
        do {
            if (!iterator.hasNext()) {
                return null;
            }

            genre = (Genre)iterator.next();
        } while(code != genre.getCode());

        return genre;
    }
}

