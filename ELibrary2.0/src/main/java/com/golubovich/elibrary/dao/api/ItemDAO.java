package com.golubovich.elibrary.dao.api;

import com.golubovich.elibrary.dao.DAOException;
import java.util.List;

public interface ItemDAO<T> {

  boolean create(T item) throws DAOException;

  List<T> read() throws DAOException;

  void update(T currentItem, T updatedItem) throws DAOException;

  boolean delete(T deletedItem) throws DAOException;

  T findByCode(int code) throws DAOException;
}

