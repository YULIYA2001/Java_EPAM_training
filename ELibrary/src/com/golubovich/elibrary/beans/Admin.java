package com.golubovich.elibrary.beans;

public class Admin extends Person {
    public Admin(String surname, String name, String patronymic, String password) {
        super(surname, name, patronymic, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
