package com.golubovich.elibrary.enums;

public enum ClientStatus {
    BEGINNER("Начинающий"),
    VISITOR("Посетитель"),
    READER("Читатель"),
    REGULAR_READER("Постоянный_читатель"),
    UNDEFINED("-");

    private final String name;

    private ClientStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ClientStatus takeByName(String name) {

        switch(name) {
            case "Начинающий":
                return BEGINNER;
            case "Посетитель":
                return VISITOR;
            case "Читатель":
                return READER;
            case "Постоянный_читатель":
                return REGULAR_READER;
            default:
                return UNDEFINED;
        }
    }
}

