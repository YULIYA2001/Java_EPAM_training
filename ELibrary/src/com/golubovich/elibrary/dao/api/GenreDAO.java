package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Genre;
import java.util.List;

public interface GenreDAO {
    boolean create(Genre var1);
    List<Genre> read();

    Genre findByCode(int code);
}
