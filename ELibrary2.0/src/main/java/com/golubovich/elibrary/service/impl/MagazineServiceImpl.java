package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.EMPTY_MAGAZINES_LIST;
import static com.golubovich.elibrary.utils.Constants.NO_REVIEWS;
import static com.golubovich.elibrary.utils.Constants.REVIEWS;
import static com.golubovich.elibrary.utils.Constants.WRONG_MAGAZINE_CODE;

import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MagazineServiceImpl implements ItemService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final ItemDAO<Magazine> magazineDAO = provider.getMagazineDAO();

  public boolean add(String[] orderedParams) throws ServiceException {
    String name = orderedParams[0].replace('_', ' ');
    List<String> reviews = new ArrayList<>();
    String language = orderedParams[1];

    int number;
    int month;
    int year;
    try {
      number = Integer.parseInt(orderedParams[2]);
      year = Integer.parseInt(orderedParams[3]);
      month = Integer.parseInt(orderedParams[4]);
    } catch (NumberFormatException e) {
      throw new ServiceException(e);
    }

    Calendar calendar = new GregorianCalendar(year, month, 0);
    Date date = calendar.getTime();

    Magazine magazine = new Magazine(name, reviews, language, number, date);
    try {
      return magazineDAO.create(magazine);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showAll() throws ServiceException {
    List<Magazine> magazines;
    try {
      magazines = magazineDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (magazines != null && !magazines.isEmpty()) {
      Collections.sort(magazines);
      return objectListToString(magazines);
    }
    return EMPTY_MAGAZINES_LIST;
  }

  public boolean deleteByCode(int code) throws ServiceException {

    try {
      Magazine deletedMagazine = magazineDAO.findByCode(code);

      if (deletedMagazine != null && magazineDAO.delete(deletedMagazine)) {
        Magazine.decrementCount();
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public boolean addReviewByCode(int code, String review) throws ServiceException {
    try {
      Magazine currentMagazine = magazineDAO.findByCode(code);

      if (currentMagazine != null) {
        Magazine updatedMagazine = new Magazine(currentMagazine);
        updatedMagazine.getReview().add(review);
        magazineDAO.update(currentMagazine, updatedMagazine);
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showReviewsByCode(int code) throws ServiceException {
    Magazine magazine;
    try {
      magazine = magazineDAO.findByCode(code);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (magazine == null) {
      return WRONG_MAGAZINE_CODE;
    }
    if (magazine.getReview().isEmpty()) {
      return REVIEWS + " (" + magazine.getCode() + ", " + magazine.getName() + "):\n" +
          NO_REVIEWS;
    }
    return REVIEWS + " (" +  magazine.getCode() + ", " + magazine.getName() + "):\n" +
        objectListToString(magazine.getReview());
  }
}

