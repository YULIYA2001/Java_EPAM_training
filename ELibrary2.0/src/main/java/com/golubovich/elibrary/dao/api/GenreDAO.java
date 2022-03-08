package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOException;
import java.util.List;

public interface GenreDAO {

  boolean create(Genre var1) throws DAOException;

  List<Genre> read() throws DAOException;

  Genre findByCode(int code) throws DAOException;
}
