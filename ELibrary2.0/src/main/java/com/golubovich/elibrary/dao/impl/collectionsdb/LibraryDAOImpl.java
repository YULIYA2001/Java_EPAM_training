package com.golubovich.elibrary.dao.impl.collectionsdb;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.dao.api.LibraryDAO;
import com.golubovich.elibrary.utils.DataSource;

public class LibraryDAOImpl implements LibraryDAO {

  private final DataSource dataSource = DataSource.getInstance();

  public void update(Library updatedLibrary) {
    dataSource.setLibrary(updatedLibrary);
  }

  public Library read() {
    return this.dataSource.getLibrary();
  }
}

