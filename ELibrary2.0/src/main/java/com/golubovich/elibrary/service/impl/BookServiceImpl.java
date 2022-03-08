package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.EMPTY_BOOKS_LIST;
import static com.golubovich.elibrary.utils.Constants.NO_REVIEWS;
import static com.golubovich.elibrary.utils.Constants.REVIEWS;
import static com.golubovich.elibrary.utils.Constants.WRONG_BOOK_CODE;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements ItemService {

  private final DAOProvider provider = DAOProvider.getInstance();
  private final ItemDAO<Book> bookDAO = provider.getBookDAO();

  public boolean add(String[] orderedParams) throws ServiceException {

    String name = orderedParams[0].replace('_', ' ');
    List<String> review = new ArrayList<>();
    String language = orderedParams[1];
    String author = orderedParams[3].replace('_', ' ');

    try {
      int code = Integer.parseInt(orderedParams[2]);
      Genre genre = provider.getGenreDAO().findByCode(code);
      if (genre != null) {
        Book book = new Book(name, review, language, genre, author);
        return bookDAO.create(book);
      }
      return false;

    } catch (DAOException | NumberFormatException e) {
      throw new ServiceException(e);
    }
  }

  public String showAll() throws ServiceException {
    List<Book> books;
    try {
      books = bookDAO.read();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (books != null && !books.isEmpty()) {
      Collections.sort(books);
      return objectListToString(books);
    }
    return EMPTY_BOOKS_LIST;
  }

  public boolean deleteByCode(int code) throws ServiceException {
    try {
      Book deletedBook = bookDAO.findByCode(code);

      if (deletedBook != null && bookDAO.delete(deletedBook)) {
        Book.decrementCount();
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public boolean addReviewByCode(int code, String review) throws ServiceException {
    try {
      Book currentBook = bookDAO.findByCode(code);

      if (currentBook != null) {
        Book updatedBook = new Book(currentBook);
        updatedBook.getReview().add(review);
        bookDAO.update(currentBook, updatedBook);
        return true;
      }
      return false;

    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  public String showReviewsByCode(int code) throws ServiceException {
    Book book;
    try {
      book = bookDAO.findByCode(code);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }

    if (book == null) {
      return WRONG_BOOK_CODE;
    }
    if (book.getReview().isEmpty()) {
      return REVIEWS + " (" + book.getCode() + ", " + book.getName() + "):\n" + NO_REVIEWS;
    }
    return REVIEWS + " (" + book.getCode() + ", " + book.getName() + "):\n" +
        objectListToString(book.getReview());
  }
}

