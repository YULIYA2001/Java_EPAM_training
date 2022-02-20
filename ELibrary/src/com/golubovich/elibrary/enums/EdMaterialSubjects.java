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
        EdMaterialSubjects[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EdMaterialSubjects subject = var1[var3];
            if (code == subject.code) {
                return subject;
            }
        }

        return null;
    }
}

