package com.golubovich.elibrary.beans;

public class Admin extends Person {
    public Admin(String surname, String name, String patronymic, String password) {
        super(surname, name, patronymic, password);
    }

    public Admin(String surname, String name, String password) {
        super(surname, name, password);
    }

    public String toString() {
        return "Admin{} " + super.toString();
    }
}
