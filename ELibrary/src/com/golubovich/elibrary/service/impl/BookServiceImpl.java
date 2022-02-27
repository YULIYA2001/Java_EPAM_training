package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO<Book> bookDAO = provider.getBookDAO();

    public boolean add(String[] orderedParams) {

        String name = orderedParams[0];
        List<String> review = new ArrayList<>();
        String language = orderedParams[1];
        Genre genre = provider.getGenreDAO().findByCode(Integer.parseInt(orderedParams[2]));
        String author = orderedParams[4];

        if (genre != null) {
            Book book = new Book(name, review, language, genre, author);
            return bookDAO.create(book);
        }
        return false;
    }

    public String showAll() {
        List<Book> books = bookDAO.read();
        if (books != null && !books.isEmpty()) {
            return objectListToString(books);
        }
        return "Список книг пуст";
    }

    public boolean deleteByCode(int code) {
        Book deletedBook = bookDAO.findByCode(code);

        if (deletedBook != null) {
            return bookDAO.delete(deletedBook);
        }
        return false;
    }

    public boolean addReviewByCode(int code, String review) {
        Book currentBook = bookDAO.findByCode(code);

        if (currentBook != null) {
            Book updatedBook = new Book(currentBook);
            updatedBook.getReview().add(review);
            bookDAO.update(currentBook, updatedBook);
            return true;
        }
        return false;
    }

    public String showReviewsByCode(int code) {
        Book book = bookDAO.findByCode(code);

        if (book == null) {
            return "Неверный код книги";
        }
        if (book.getReview().isEmpty()) {
            return "Отзывов нет";
        }
        return "Отзывы (код книги " + book.getCode() + ", " + book.getName() + "):\n" +
                objectListToString(book.getReview());
    }
}

