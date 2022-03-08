package com.golubovich.elibrary.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Book extends Item implements Serializable {

  private Genre genre;
  private String author;
  private static int count;

  public Book() {
  }

  public Book(String name, List<String> review, String language, Genre genre, String author) {
    super(name, review, language);
    this.genre = genre;
    this.author = author;
    count++;
  }

  public Book(Book copiedBook) {
    super(copiedBook.getCode(), copiedBook.getName(), copiedBook.getReview(),
        copiedBook.getLanguage());
    this.genre = copiedBook.genre;
    this.author = copiedBook.author;
  }

  public static int getCount() {
    return count;
  }

  public static void decrementCount() {
    count--;
  }

  public Genre getGenre() {
    return this.genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      if (!super.equals(o)) {
        return false;
      } else {
        Book book = (Book) o;
        return this.genre.equals(book.genre) && Objects.equals(this.author, book.author);
      }
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.genre, this.author);
  }

  @Override
  public String toString() {
    return "Книга  -  " + super.toString() + "   Автор: " + this.author + "   " + this.genre
        .toString();
  }
}
