package com.golubovich.elibrary.enums;

public enum EdMaterialSubjects {
    BIOLOGY("Биология", 1),
    HISTORY("История", 2),
    INFORMATICS("Информатика", 3),
    LANGUAGES("Языки", 4),
    LITERATURE("Литература", 5),
    MATH("Математика", 6),
    PHYSICS("Физика", 7),
    DEFAULT("Выход", 0);

    private final String name;
    private final int code;

    private EdMaterialSubjects(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public static EdMaterialSubjects getByCode(int code) {
        int count = values().length;

        for(int i = 0; i < count; ++i) {
            EdMaterialSubjects subject = values()[i];
            if (code == subject.code) {
                return subject;
            }
        }
        return null;
    }
}

