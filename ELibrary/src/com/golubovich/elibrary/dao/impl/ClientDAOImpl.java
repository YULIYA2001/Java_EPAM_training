package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.source.DataSource;
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

    public boolean update(Client updatedClient) {
        Client currentClient = findByEmail(updatedClient.getEMail());

        if (currentClient != null) {
            dataSource.getClients().set(dataSource.getClients().indexOf(currentClient), updatedClient);
            return true;
        }
        return false;
    }

    public boolean delete(Client deletedClient) {
        Client currentClient = findByEmail(deletedClient.getEMail());

        if (currentClient != null) {
            dataSource.getClients().remove(currentClient);
            return true;
        }
        return false;
    }

    public Client findByEmail(String eMail) {
        Iterator<Client> clientsIterator = dataSource.getClients().iterator();
        Client client;

        while(clientsIterator.hasNext()) {
            client = clientsIterator.next();
            if (client.getEMail().equals(eMail)) {
                return client;
            }
        }
        return null;
    }
}

