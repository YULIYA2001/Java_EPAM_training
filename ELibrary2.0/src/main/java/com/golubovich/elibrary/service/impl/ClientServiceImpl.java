package com.golubovich.elibrary.service.impl;


import static com.golubovich.elibrary.utils.Constants.CLIENT_NOT_FOUND;
import static com.golubovich.elibrary.utils.Constants.EMPTY_CLIENTS_LIST;
import static com.golubovich.elibrary.utils.Constants.NO_CHANGES;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.ClientService;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final ClientDAO clientDAO = provider.getClientDAO();

  public boolean register(String[] orderedFields) throws ServiceException {

    String surname = orderedFields[0];
    String name = orderedFields[1];
    String patronymic = orderedFields[2];
    String password = orderedFields[3];
    int age = Integer.parseInt(orderedFields[4]);
    String eMail = orderedFields[5];

    if (NO_CHANGES.equals(patronymic)) {
      patronymic = "";
    }

    Client client = new Client(surname, name, patronymic, password, age, eMail,
        new Date(), ClientStatus.BEGINNER);

    try {

      if (clientDAO.findByEmail(eMail) == null) {
        return clientDAO.create(client);
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showAll() throws ServiceException {
    List<Client> clients;
    try {
      clients = clientDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (clients != null && !clients.isEmpty()) {
      Collections.sort(clients);
      // clients.sort(new CompareClientsByDate());
      return objectListToString(clients);
    }
    return EMPTY_CLIENTS_LIST;
  }

  public boolean changeData(String eMail, String surname, String name, String patronymic,
      String password, ClientStatus clientStatus) throws ServiceException {
    if (patronymic == null) {
      patronymic = "";
    }

    Client currentClient;
    try {
      currentClient = clientDAO.findByEmail(eMail);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (currentClient != null) {
      Client updatedClient = new Client(currentClient);

      if (!surname.equals(NO_CHANGES)) {
        updatedClient.setSurname(surname);
      }
      if (!name.equals(NO_CHANGES)) {
        updatedClient.setName(name);
      }
      if (!patronymic.equals(NO_CHANGES)) {
        updatedClient.setPatronymic(patronymic);
      }
      if (!password.equals(NO_CHANGES)) {
        updatedClient.setPassword(password);
      }
      if (clientStatus != ClientStatus.UNDEFINED) {
        updatedClient.setClientStatus(clientStatus);
      }

      try {
        clientDAO.update(currentClient, updatedClient);
      } catch (DAOException e) {
        throw new ServiceException(e);
      }

      return true;
    }
    return false;
  }

  public boolean deleteByEMail(String eMail) throws ServiceException {
    try {
      Client deletedClient = clientDAO.findByEmail(eMail);
      if (deletedClient != null && clientDAO.delete(deletedClient)) {
        Client.decrementCount();
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showByEMail(String eMail) throws ServiceException {
    Client client;
    try {
      client = clientDAO.findByEmail(eMail);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (client != null) {
      return client.toString();
    }
    return CLIENT_NOT_FOUND;
  }

  public boolean authorize(String eMail, String password) throws ServiceException {
    Client client;
    try {
      client = clientDAO.findByEmail(eMail);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (client != null) {
      return client.getPassword().equals(password);
    }
    return false;
  }


  private String objectListToString(List<Client> clients) {
    StringBuilder clientsString = new StringBuilder();

    for (Client client : clients) {
      clientsString.append(client.toString()).append("\n");
    }

    return String.valueOf(clientsString).substring(0, clientsString.length() - 1);
  }
}

