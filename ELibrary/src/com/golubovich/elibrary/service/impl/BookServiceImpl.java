package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO bookDAO;

    public BookServiceImpl() {
        this.bookDAO = this.provider.getBookDAO();
    }

    public boolean add(String[] orderedParams) {
        ItemDAO bookDAO = this.provider.getBookDAO();
        Genre genre = this.provider.getGenreDAO().read(Integer.parseInt(orderedParams[2]));
        if (genre == null) {
        }

        Book book = new Book(orderedParams[0], new ArrayList(), orderedParams[1], genre, orderedParams[3]);
        boolean result = bookDAO.create(book);
        return result;
    }

    public String showReviewsByCode(int code) {
        Book book = (Book)this.bookDAO.readReviews(code);
        if (book == null) {
            return "Неверный код книги";
        } else if (book.getReview().isEmpty()) {
            return "Отзывов нет";
        } else {
            StringBuilder result = new StringBuilder();
            int var10001 = book.getCode();
            result.append("Отзывы (код книги " + var10001 + " " + book.getName() + "):\n");
            Iterator var4 = book.getReview().iterator();

            while(var4.hasNext()) {
                String review = (String)var4.next();
                result.append(" ").append(review).append("\n");
            }

            return String.valueOf(result);
        }
    }

    public boolean addReviewByCode(int code, String review) {
        boolean result = this.bookDAO.addReview(code, review);
        return result;
    }

    public String showAll() {
        List<Book> books = this.bookDAO.readAll();
        if (books != null && !books.isEmpty()) {
            StringBuilder result = new StringBuilder();
            Iterator var3 = books.iterator();

            while(var3.hasNext()) {
                Book book = (Book)var3.next();
                result.append(" ").append(book.toReadableString()).append("\n");
            }

            return String.valueOf(result);
        } else {
            return "Список книг пуст";
        }
    }

    public boolean deleteByCode(int code) {
        boolean result = this.bookDAO.delete(code);
        return result;
    }
}

