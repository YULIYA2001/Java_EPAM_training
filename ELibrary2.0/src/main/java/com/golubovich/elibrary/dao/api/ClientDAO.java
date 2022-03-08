package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.DAOException;
import java.util.List;

public interface ClientDAO {

  boolean create(Client var1) throws DAOException;

  List<Client> read() throws DAOException;

  void update(Client currentClient, Client updatedClient) throws DAOException;

  boolean delete(Client deletedClient) throws DAOException;

  Client findByEmail(String eMail) throws DAOException;
}
