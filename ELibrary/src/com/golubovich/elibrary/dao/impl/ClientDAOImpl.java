package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.source.DataSource;
import java.util.Iterator;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final DataSource dataSource = DataSource.getInstance();

    public ClientDAOImpl() {
    }

    public boolean authorize(String eMail, String password) {
        Iterator var3 = this.dataSource.getClients().iterator();

        Client client;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            client = (Client)var3.next();
        } while(!client.geteMail().equals(eMail) || !client.getPassword().equals(password));

        return true;
    }

    public boolean create(Client client) {
        boolean result = this.dataSource.getClients().add(client);
        return result;
    }

    public boolean update(String eMail, String surname, String name, String patronymic, String password, ClientStatus clientStatus) {
        Iterator var7 = this.dataSource.getClients().iterator();

        Client client;
        do {
            if (!var7.hasNext()) {
                return false;
            }

            client = (Client)var7.next();
        } while(!client.geteMail().equals(eMail));

        if (!surname.equals("-")) {
            client.setSurname(surname);
        }

        if (!name.equals("-")) {
            client.setName(name);
        }

        if (!patronymic.equals("-")) {
            client.setPatronymic(patronymic);
        }

        if (!password.equals("-")) {
            client.setPassword(password);
        }

        if (clientStatus != ClientStatus.UNDEFINED) {
            client.setClientStatus(clientStatus);
        }

        return true;
    }

    public boolean delete(String eMail) {
        Iterator iterator = this.dataSource.getClients().iterator();

        Client client;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            client = (Client)iterator.next();
        } while(!client.geteMail().equals(eMail));

        this.dataSource.getClients().remove(client);
        return true;
    }

    public Client read(String eMail) {
        Iterator var2 = this.dataSource.getClients().iterator();

        Client client;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            client = (Client)var2.next();
        } while(!client.geteMail().equals(eMail));

        return client;
    }

    public List<Client> readAll() {
        return this.dataSource.getClients();
    }

    public boolean isClient(String eMail) {
        Iterator var2 = this.dataSource.getClients().iterator();

        Client client;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            client = (Client)var2.next();
        } while(!client.geteMail().equals(eMail));

        return true;
    }
}

