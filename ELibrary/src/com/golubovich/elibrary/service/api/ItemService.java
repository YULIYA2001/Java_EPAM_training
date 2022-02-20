package com.golubovich.elibrary.service.api;

public interface ItemService {
    boolean add(String[] var1);

    String showReviewsByCode(int var1);

    boolean addReviewByCode(int var1, String var2);

    String showAll();

    boolean deleteByCode(int var1);
}
