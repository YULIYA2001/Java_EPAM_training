package com.golubovich.elibrary.beans;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    public Admin() {}

    public Admin(String surname, String name, String patronymic, String password) {
        super(surname, name, patronymic, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
