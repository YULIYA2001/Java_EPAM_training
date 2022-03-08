package com.golubovich.elibrary.enums;

public enum ItemType {
  BOOK("Кинига"),
  EDUCATIONAL_MATERIAL("Учебный материал"),
  MAGAZINE("Журнал");

  private final String name;

  private ItemType(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}

