package com.golubovich.elibrary.service.api;

import com.golubovich.elibrary.service.ServiceException;
import java.util.List;

public interface ItemService {

  boolean add(String[] orderedParams) throws ServiceException;

  String showAll() throws ServiceException;

  boolean deleteByCode(int code) throws ServiceException;

  boolean addReviewByCode(int code, String review) throws ServiceException;

  String showReviewsByCode(int code) throws ServiceException;

  default <T> String objectListToString(List<T> items) {
    StringBuilder itemsString = new StringBuilder();

    for (T item : items) {
      itemsString.append(item.toString()).append("\n");
    }
    return String.valueOf(itemsString).substring(0, itemsString.length() - 1);
  }
}
