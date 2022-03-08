package com.golubovich.elibrary.dao.impl.collectionsdb;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.utils.DataSource;
import java.util.Iterator;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

  private final DataSource dataSource = DataSource.getInstance();

  public boolean create(Client client) {
    return dataSource.getClients().add(client);
  }

  public List<Client> read() {
    return this.dataSource.getClients();
  }

  public void update(Client currentClient, Client updatedClient) {
    dataSource.getClients().set(dataSource.getClients().indexOf(currentClient), updatedClient);
  }

  public boolean delete(Client deletedClient) {
    return dataSource.getClients().remove(deletedClient);
  }

  public Client findByEmail(String eMail) {
    Iterator<Client> clientsIterator = dataSource.getClients().iterator();
    Client client;

    while (clientsIterator.hasNext()) {
      client = clientsIterator.next();
      if (client.getEMail().equals(eMail)) {
        return client;
      }
    }
    return null;
  }
}

