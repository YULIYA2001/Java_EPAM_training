package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class MagazineServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO magazineDAO;

    public MagazineServiceImpl() {
        this.magazineDAO = this.provider.getMagazineDAO();
    }

    public boolean add(String[] orderedParams) {
        Calendar calendar = new GregorianCalendar(Integer.parseInt(orderedParams[3]), Integer.parseInt(orderedParams[4]), 0);
        Date date = calendar.getTime();
        Magazine magazine = new Magazine(orderedParams[0], new ArrayList(), orderedParams[1], Integer.parseInt(orderedParams[2]), date);
        boolean result = this.magazineDAO.create(magazine);
        return result;
    }

    public String showReviewsByCode(int code) {
        Magazine magazine = (Magazine)this.magazineDAO.readReviews(code);
        if (magazine == null) {
            return "Неверный код журнала";
        } else if (magazine.getReview().isEmpty()) {
            return "Отзывов нет";
        } else {
            StringBuilder result = new StringBuilder();
            int var10001 = magazine.getCode();
            result.append("Отзывы (код журнала " + var10001 + magazine.getName() + "):\n");
            Iterator var4 = magazine.getReview().iterator();

            while(var4.hasNext()) {
                String review = (String)var4.next();
                result.append(" ").append(review).append("\n");
            }

            return String.valueOf(result);
        }
    }

    public boolean addReviewByCode(int code, String review) {
        boolean result = this.magazineDAO.addReview(code, review);
        return result;
    }

    public String showAll() {
        List<Magazine> magazines = this.magazineDAO.readAll();
        if (magazines != null && !magazines.isEmpty()) {
            StringBuilder result = new StringBuilder();
            Iterator var3 = magazines.iterator();

            while(var3.hasNext()) {
                Magazine magazine = (Magazine)var3.next();
                result.append(" ").append(magazine.toReadableString()).append("\n");
            }

            return String.valueOf(result);
        } else {
            return "Список журналов пуст";
        }
    }

    public boolean deleteByCode(int code) {
        boolean result = this.magazineDAO.delete(code);
        return result;
    }
}

