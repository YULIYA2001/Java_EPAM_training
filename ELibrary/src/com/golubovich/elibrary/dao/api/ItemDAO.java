package com.golubovich.elibrary.dao.api;

import java.util.List;

public interface ItemDAO<T> {
    boolean create(T var1);

    T readReviews(int var1);

    boolean addReview(int var1, String var2);

    List<T> readAll();

    boolean delete(int var1);
}

