package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.DAOException;

public interface LibraryDAO {

  Library read() throws DAOException;

  void update(Library updatedLibrary) throws DAOException;
}

