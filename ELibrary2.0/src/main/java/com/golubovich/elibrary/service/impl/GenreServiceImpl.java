package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.EMPTY_GENRES_LIST;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.GenreDAO;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.GenreService;
import java.util.Collections;
import java.util.List;

public class GenreServiceImpl implements GenreService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final GenreDAO genreDAO = provider.getGenreDAO();

  public boolean add(String name, String description) throws ServiceException {
    Genre genre = new Genre(
        name.replace('_', ' '), description.replace('_', ' ')
    );

    try {
      return genreDAO.create(genre);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showAll() throws ServiceException {
    List<Genre> genres;
    try {
      genres = genreDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (genres != null && !genres.isEmpty()) {
      Collections.sort(genres);
      return genresListToString(genres);
    }
    return EMPTY_GENRES_LIST;
  }

  private String genresListToString(List<Genre> genres) {
    StringBuilder genresString = new StringBuilder();

    for (Genre genre : genres) {
      genresString.append(genre.toString()).append("\n");
    }
    return String.valueOf(genresString);
  }
}

