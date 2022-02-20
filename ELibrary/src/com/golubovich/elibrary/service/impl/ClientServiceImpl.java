package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.api.ClientService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final DAOProvider provider = DAOProvider.getInstance();

    public ClientServiceImpl() {
    }

    public boolean authorize(String eMail, String password) {
        ClientDAO clientDAO = this.provider.getClientDAO();
        boolean result = clientDAO.authorize(eMail, password);
        return result;
    }

    public boolean register(String[] orderedParams) {
        ClientDAO clientDAO = this.provider.getClientDAO();
        if ("-".equals(orderedParams[2])) {
            orderedParams[2] = "";
        }

        Client client = new Client(orderedParams[0], orderedParams[1], orderedParams[2], orderedParams[3], Integer.parseInt(orderedParams[4]), orderedParams[5], new Date(), ClientStatus.BEGINNER);
        if (!clientDAO.isClient(client.geteMail())) {
            boolean result = clientDAO.create(client);
            return result;
        } else {
            return false;
        }
    }

    public boolean changeData(String eMail, String surname, String name, String patronymic, String password, ClientStatus clientStatus) {
        if (patronymic == null) {
            patronymic = "";
        }

        ClientDAO clientDAO = this.provider.getClientDAO();
        boolean result = clientDAO.update(eMail, surname, name, patronymic, password, clientStatus);
        return result;
    }

    public boolean deleteByEMail(String eMail) {
        ClientDAO clientDAO = this.provider.getClientDAO();
        boolean result = clientDAO.delete(eMail);
        return result;
    }

    public String findByEMail(String eMail) {
        ClientDAO clientDAO = this.provider.getClientDAO();
        Client client = clientDAO.read(eMail);
        return client != null ? client.toReadableString() : "error Client not found";
    }

    public String showAll() {
        ClientDAO clientDAO = this.provider.getClientDAO();
        List<Client> clients = clientDAO.readAll();
        if (clients != null && !clients.isEmpty()) {
            StringBuilder response = new StringBuilder();
            Iterator var4 = clients.iterator();

            while(var4.hasNext()) {
                Client client = (Client)var4.next();
                response.append(" ").append(client.toReadableString()).append("\n");
            }

            return String.valueOf(response);
        } else {
            return "Нет зарегистрированных клиентов";
        }
    }
}

