package com.golubovich.elibrary.service.api;


public interface AdminService {
    String show();
    boolean changeData(String surname, String name, String patronymic, String password);

    boolean authorize(String password);// throws ServiceException;
}
