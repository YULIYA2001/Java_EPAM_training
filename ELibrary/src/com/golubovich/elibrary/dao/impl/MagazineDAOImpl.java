package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.source.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MagazineDAOImpl implements ItemDAO<Magazine> {
    private final DataSource dataSource = DataSource.getInstance();

    public MagazineDAOImpl() {
    }

    public boolean create(Magazine bean) {
        boolean result = ((List)this.dataSource.getItems().get(ItemType.MAGAZINE)).add(bean);
        return result;
    }

    public Magazine readReviews(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.MAGAZINE)).iterator();

        Magazine magazine;
        do {
            if (!iterator.hasNext()) {
                return null;
            }

            magazine = (Magazine)iterator.next();
        } while(magazine.getCode() != code);

        return magazine;
    }

    public boolean addReview(int code, String review) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.MAGAZINE)).iterator();

        Magazine magazine;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            magazine = (Magazine)iterator.next();
        } while(magazine.getCode() != code);

        magazine.getReview().add(review);
        return true;
    }

    public List<Magazine> readAll() {
        return new ArrayList((Collection)this.dataSource.getItems().get(ItemType.MAGAZINE));
    }

    public boolean delete(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.MAGAZINE)).iterator();

        Magazine magazine;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            magazine = (Magazine)iterator.next();
        } while(magazine.getCode() != code);

        ((List)this.dataSource.getItems().get(ItemType.MAGAZINE)).remove(magazine);
        return true;
    }
}

