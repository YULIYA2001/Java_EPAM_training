package com.golubovich.elibrary.enums;

public enum EdMaterialType {
  HANDBOOK("Справочник"),
  METHODOLOGICAL_MANUAL("Методическое_пособие"),
  TEXTBOOK("Учебник"),
  TASKBOOK("Сборник_задач/упражнений"),
  DEFAULT("Не_определено");

  private final String name;

  EdMaterialType(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static EdMaterialType getByName(String name) {
    int count = values().length;

    for (int i = 0; i < count; ++i) {
      EdMaterialType type = values()[i];
      if (name.equals(type.getName())) {
        return type;
      }
    }
    return null;
  }
}

