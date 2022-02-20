package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.exceptions.ServiceException;

public interface AdminService {
    boolean changeData(String var1, String var2, String var3, String var4);

    boolean authorize(String var1) throws ServiceException;

    String show();
}
