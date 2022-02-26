package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.enums.ClientStatus;

public interface ClientService {
    boolean register(String[] orderedFields);
    String showAll();
    boolean changeData(String eMail, String surname, String name, String patronymic,
                       String password, ClientStatus clientStatus);
    boolean deleteByEMail(String var1);

    boolean authorize(String eMail, String password);
    String showByEMail(String eMail);
}
