package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
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

    public boolean add(String[] orderedParams) {
        String name = orderedParams[0];
        List<String> reviews = new ArrayList<>();
        String language = orderedParams[1];
        int number = Integer.parseInt(orderedParams[2]);

        Calendar calendar = new GregorianCalendar(
                Integer.parseInt(orderedParams[3]), Integer.parseInt(orderedParams[4]), 0);
        Date date = calendar.getTime();

        Magazine magazine = new Magazine(name, reviews, language, number, date);
        return magazineDAO.create(magazine);
    }

    public String showAll() {
        List<Magazine> magazines = magazineDAO.read();
        if (magazines != null && !magazines.isEmpty()) {
            Collections.sort(magazines);
            return objectListToString(magazines);
        }
        return "Список журналов пуст";
    }

    public boolean deleteByCode(int code) {
        Magazine deletedMagazine = magazineDAO.findByCode(code);

        if (deletedMagazine != null) {
            return magazineDAO.delete(deletedMagazine);
        }
        return false;
    }

    public boolean addReviewByCode(int code, String review) {
        Magazine currentMagazine = magazineDAO.findByCode(code);

        if (currentMagazine != null) {
            Magazine updatedMagazine = new Magazine(currentMagazine);
            updatedMagazine.getReview().add(review);
            magazineDAO.update(currentMagazine, updatedMagazine);
            return true;
        }
        return false;
    }

    public String showReviewsByCode(int code) {
        Magazine magazine = magazineDAO.findByCode(code);

        if (magazine == null) {
            return "Неверный код журнала";
        }
        if (magazine.getReview().isEmpty()) {
            return "Отзывов нет";
        }
        return "Отзывы (код журнала " + magazine.getCode() + ", " + magazine.getName() + "):\n" +
                objectListToString(magazine.getReview());
    }
}

