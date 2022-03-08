package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.service.ServiceException;

public interface GenreService {

  boolean add(String name, String description) throws ServiceException;

  String showAll() throws ServiceException;
}

