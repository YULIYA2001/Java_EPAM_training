package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Client;
import java.util.List;

public interface ClientDAO {
    boolean create(Client var1);
    List<Client> read();
    boolean update(Client updatedClient);
    boolean delete(Client deletedClient);

    Client findByEmail(String eMail);
}
