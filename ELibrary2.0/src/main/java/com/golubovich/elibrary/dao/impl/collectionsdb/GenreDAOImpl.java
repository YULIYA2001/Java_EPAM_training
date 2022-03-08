package com.golubovich.elibrary.dao.impl.collectionsdb;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.utils.DataSource;
import java.util.Iterator;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

  private final DataSource dataSource = DataSource.getInstance();

  public boolean create(Genre genre) {
    return dataSource.getGenres().add(genre);
  }

  public List<Genre> read() {
    return dataSource.getGenres();
  }

  public Genre findByCode(int code) {
    Iterator<Genre> genresIterator = this.dataSource.getGenres().iterator();

    Genre genre;
    while (genresIterator.hasNext()) {
      genre = genresIterator.next();
      if (genre.getCode() == code) {
        return genre;
      }
    }

    return null;
  }
}

