package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.enums.ClientStatus;

public interface ClientService {
    boolean authorize(String var1, String var2);

    boolean register(String[] var1);

    boolean changeData(String var1, String var2, String var3, String var4, String var5, ClientStatus var6);

    boolean deleteByEMail(String var1);

    String findByEMail(String var1);

    String showAll();
}
