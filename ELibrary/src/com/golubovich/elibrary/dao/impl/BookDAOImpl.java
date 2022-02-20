package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.source.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BookDAOImpl implements ItemDAO<Book> {
    private final DataSource dataSource = DataSource.getInstance();

    public BookDAOImpl() {
    }

    public boolean create(Book bean) {
        boolean result = ((List)this.dataSource.getItems().get(ItemType.BOOK)).add(bean);
        return result;
    }

    public Book readReviews(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.BOOK)).iterator();

        Book book;
        do {
            if (!iterator.hasNext()) {
                return null;
            }

            book = (Book)iterator.next();
        } while(book.getCode() != code);

        return book;
    }

    public boolean addReview(int code, String review) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.BOOK)).iterator();

        Book book;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            book = (Book)iterator.next();
        } while(book.getCode() != code);

        book.getReview().add(review);
        return true;
    }

    public List<Book> readAll() {
        return new ArrayList((Collection)this.dataSource.getItems().get(ItemType.BOOK));
    }

    public boolean delete(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.BOOK)).iterator();

        Book book;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            book = (Book)iterator.next();
        } while(book.getCode() != code);

        ((List)this.dataSource.getItems().get(ItemType.BOOK)).remove(book);
        return true;
    }
}
