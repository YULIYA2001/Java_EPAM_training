package com.golubovich.elibrary.beans;

import java.util.Objects;

public class Genre {
    private static int codeCount = 0;
    private int code;
    private String name;
    private String description;

    public Genre(String genreName, String genreDescription) {
        this.code = ++codeCount;
        this.name = genreName;
        this.description = genreDescription;
    }

    public int getCode() {
        return this.code;
    }

    public static int getCodeCount() {
        return codeCount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Genre genre = (Genre)o;
            return this.name.equals(genre.name) && Objects.equals(this.description, genre.description);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.description});
    }

    public String toString() {
        return "Genre{genreName='" + this.name + "', genreDescription='" + this.description + "'}";
    }

    public String toReadableString() {
        return this.code + ". Жанр: " + this.name + "  (описание: " + this.description + ")";
    }
}
