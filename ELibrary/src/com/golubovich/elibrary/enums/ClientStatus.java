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
        byte var2 = -1;
        switch(name.hashCode()) {
            case -1609053245:
                if (name.equals("Постоянный_читатель")) {
                    var2 = 3;
                }
                break;
            case -1478194643:
                if (name.equals("Посетитель")) {
                    var2 = 1;
                }
                break;
            case -1466295085:
                if (name.equals("Начинающий")) {
                    var2 = 0;
                }
                break;
            case 72342595:
                if (name.equals("Читатель")) {
                    var2 = 2;
                }
        }

        switch(var2) {
            case 0:
                return BEGINNER;
            case 1:
                return VISITOR;
            case 2:
                return READER;
            case 3:
                return REGULAR_READER;
            default:
                return UNDEFINED;
        }
    }
}

