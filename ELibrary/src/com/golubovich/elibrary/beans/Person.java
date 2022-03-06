package com.golubovich.elibrary.beans;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {
    private String surname;
    private String name;
    private String patronymic = "";
    private String password;

    protected Person() {}

    protected Person(String surname, String name, String patronymic, String password) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Person person = (Person)o;
            return this.surname.equals(person.surname) && this.name.equals(person.name) && Objects.equals(this.patronymic, person.patronymic) && this.password.equals(person.password);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.surname, this.name, this.patronymic, this.password);
    }

    @Override
    public String toString() {
        return "ФИО: " + this.surname + " " + this.name + " " + this.patronymic +
                "    Пароль: " + this.password;
    }
}

