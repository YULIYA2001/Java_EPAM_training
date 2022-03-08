package com.golubovich.elibrary.enums;

public enum ClientStatus {
  BEGINNER("Начинающий"),
  VISITOR("Посетитель"),
  READER("Читатель"),
  REGULAR_READER("Постоянный_читатель"),
  UNDEFINED("-");

  private final String name;

  ClientStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static ClientStatus takeByName(String name) {

      return switch (name) {
          case "Начинающий" -> BEGINNER;
          case "Посетитель" -> VISITOR;
          case "Читатель" -> READER;
          case "Постоянный_читатель" -> REGULAR_READER;
          default -> UNDEFINED;
      };
  }
}

