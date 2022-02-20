package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Library;

public interface LibraryDAO {
    boolean changeData(String var1, String var2, String var3);

    Library read();
}

