package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.exceptions.DAOException;

public interface AdminDAO {
    boolean update(String var1, String var2, String var3, String var4);

    boolean authorization(String var1) throws DAOException;

    Admin read();
}
