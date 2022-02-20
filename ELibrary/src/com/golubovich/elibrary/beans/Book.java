package com.golubovich.elibrary.beans;

import java.util.List;
import java.util.Objects;

public class Book extends Item {
    private Genre genre;
    private String author;

    public Book(String name, List<String> review, String language, Genre genre, String author) {
        super(name, review, language);
        this.genre = genre;
        this.author = author;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Book book = (Book)o;
                return this.genre.equals(book.genre) && Objects.equals(this.author, book.author);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.genre, this.author});
    }

    public String toString() {
        Genre var10000 = this.genre;
        return "Book{genre=" + var10000 + ", author='" + this.author + "'} " + super.toString();
    }

    public String toReadableString() {
        String var10000 = super.toReadableString();
        return "Книга  -  " + var10000 + "   Автор: " + this.author + this.genre.toReadableString();
    }
}
