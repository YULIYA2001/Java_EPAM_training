package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Library;

public interface LibraryDAO {
    Library read();
    void update(Library updatedLibrary);
}

