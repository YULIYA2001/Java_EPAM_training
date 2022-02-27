package com.golubovich.elibrary.service.impl;


import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.api.ClientService;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ClientDAO clientDAO = provider.getClientDAO();

    public boolean register(String[] orderedFields) {

        String surname = orderedFields[0];
        String name = orderedFields[1];
        String patronymic = orderedFields[2];
        String password = orderedFields[3];
        int age = Integer.parseInt(orderedFields[4]);
        String eMail = orderedFields[5];

        if ("-".equals(patronymic)) {
            patronymic = "";
        }

        Client client = new Client(surname, name, patronymic, password, age, eMail,
                new Date(), ClientStatus.BEGINNER);

        if (clientDAO.findByEmail(eMail) == null) {
            return clientDAO.create(client);
        }
        return false;
    }

    public String showAll() {
        List<Client> clients = clientDAO.read();

        if (clients != null && !clients.isEmpty()) {
            return objectListToString(clients);
        }
        return "Нет зарегистрированных клиентов";
    }

    public boolean changeData(String eMail, String surname, String name, String patronymic,
                              String password, ClientStatus clientStatus) {
        if (patronymic == null) {
            patronymic = "";
        }

        Client currentClient = clientDAO.findByEmail(eMail);

        if (currentClient != null) {
            Client updatedClient = new Client(currentClient);

            if (!surname.equals("-")){
                updatedClient.setSurname(surname);
            }
            if (!name.equals("-")) {
                updatedClient.setName(name);
            }
            if (!patronymic.equals("-")) {
                updatedClient.setPatronymic(patronymic);
            }
            if (!password.equals("-")) {
                updatedClient.setPassword(password);
            }
            if (clientStatus != ClientStatus.UNDEFINED) {
                updatedClient.setClientStatus(clientStatus);
            }

            clientDAO.update(currentClient, updatedClient);
            return true;
        }
        return false;
    }

    public boolean deleteByEMail(String eMail) {
        Client deletedClient = clientDAO.findByEmail(eMail);
        if (deletedClient != null) {
            return clientDAO.delete(deletedClient);
        }
        return false;
    }

    public String showByEMail(String eMail) {
        Client client = clientDAO.findByEmail(eMail);
        if (client != null) {
            return client.toString();
        }
        return "1 error Client not found | class ClientServiceImpl method showByEmail";
    }

    public boolean authorize(String eMail, String password) {
        Client client = clientDAO.findByEmail(eMail);

        if (client != null) {
            return client.getPassword().equals(password);
        }
        return false;
    }


    private String objectListToString(List<Client> clients) {
        StringBuilder clientsString = new StringBuilder();

        for (Client client : clients) {
            clientsString.append(" ").append(client.toString()).append("\n");
        }

        return String.valueOf(clientsString).substring(0, clientsString.length() - 1);
    }
}

