package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.enums.ClientStatus;
import java.util.List;

public interface ClientDAO {
    boolean authorize(String var1, String var2);

    boolean create(Client var1);

    boolean update(String var1, String var2, String var3, String var4, String var5, ClientStatus var6);

    boolean delete(String var1);

    Client read(String var1);

    List<Client> readAll();

    boolean isClient(String var1);
}
