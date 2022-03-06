package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.utils.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDAOImpl implements ItemDAO<Book> {
    private final DataSource dataSource = DataSource.getInstance();

    public boolean create(Book item) {
        return dataSource.getItems().get(ItemType.BOOK).add(item);
    }

    public List<Book> read() {
        return new ArrayList(dataSource.getItems().get(ItemType.BOOK));
    }

    public void update(Book currentItem, Book updatedItem) {
        dataSource.getItems().get(ItemType.BOOK).set(
                dataSource.getItems().get(ItemType.BOOK).indexOf(currentItem), updatedItem
        );
    }

    public boolean delete(Book deletedItem) {
        return dataSource.getItems().get(ItemType.BOOK).remove(deletedItem);
    }

    public Book findByCode(int code) {
        Iterator<Item> booksIterator = dataSource.getItems().get(ItemType.BOOK).iterator();

        Book book;
        while(booksIterator.hasNext()) {
            book = (Book)booksIterator.next();
            //TODO exception
            if(book.getCode() == code) {
                return book;
            }
        }
        return null;
    }
}
