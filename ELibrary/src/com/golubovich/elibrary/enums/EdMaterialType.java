package com.golubovich.elibrary.enums;

public enum EdMaterialType {
    HANDBOOK("Справочник"),
    METHODOLOGICAL_MANUAL("Методическое_пособие"),
    TEXTBOOK("Учебник"),
    TASKBOOK("Сборник_задач/упражнений"),
    DEFAULT("Не_определено");

    private final String name;

    private EdMaterialType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static EdMaterialType getByName(String name) {
        EdMaterialType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EdMaterialType type = var1[var3];
            if (name.equals(type.getName())) {
                return type;
            }
        }

        return null;
    }
}

