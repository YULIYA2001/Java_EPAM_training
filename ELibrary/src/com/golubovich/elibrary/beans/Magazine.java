package com.golubovich.elibrary.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Magazine extends Item {
    private int number;
    private Date date;

    public Magazine(String name, List<String> review, String language, int number, Date date) {
        super(name, review, language);
        this.number = number;
        this.date = date;
    }

    public Magazine(Magazine copiedMagazine) {
        super(copiedMagazine.getName(), copiedMagazine.getReview(), copiedMagazine.getLanguage());
        this.number = copiedMagazine.number;
        this.date = copiedMagazine.date;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Magazine magazine = (Magazine)o;
                return this.number == magazine.number && this.date.equals(magazine.date);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.number, this.date);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM.yyyy");
        return "Журнал  -  " + super.toString() +
                "   Номер: " + this.number +
                "   Дата: " + formatter.format(this.date);
    }
}

