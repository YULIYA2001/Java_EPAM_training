package com.golubovich.elibrary.service.api;

import java.util.List;

public interface ItemService {
    boolean add(String[] orderedParams);
    String showAll();
    boolean deleteByCode(int code);

    boolean addReviewByCode(int code, String review);
    String showReviewsByCode(int code);

    default <T> String objectListToString(List<T> items) {
        StringBuilder itemsString = new StringBuilder();

        for (T item : items) {
            itemsString.append(item.toString()).append("\n");
        }
        return String.valueOf(itemsString).substring(0, itemsString.length() - 1);
    }
}
