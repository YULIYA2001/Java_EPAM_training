package com.golubovich.elibrary.beans;

import java.util.List;
import java.util.Objects;

public abstract class Item implements Comparable<Item>{
    private static int codeCount = 0;
    private final int code;
    private String name;
    private List<String> review;
    private String language;


    protected Item(String name, List<String> review, String language) {
        this.code = ++codeCount;
        this.name = name;
        this.review = review;
        this.language = language;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getReview() {
        return this.review;
    }

    public void setReview(List<String> review) {
        this.review = review;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static int getCodeCount() {
        return codeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Item item = (Item)o;
            return this.code == item.code
                    && this.name.equals(item.name)
                    && Objects.equals(this.review, item.review)
                    && this.language.equals(item.language);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.name, this.review, this.language);
    }

    @Override
    public String toString() {
        return "   Код: " + this.code + "   Название: " + this.name + "   Язык: " + this.language;
    }

    @Override
    public int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }
}

