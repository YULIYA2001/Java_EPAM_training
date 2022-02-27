package com.golubovich.elibrary.dao.api;

import java.util.List;

public interface ItemDAO<T> {
    boolean create(T item);
    List<T> read();
    void update(T currentItem, T updatedItem);
    boolean delete(T deletedItem);

    T findByCode(int code);
}

