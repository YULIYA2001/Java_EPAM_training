package com.golubovich.elibrary;

import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT_TRY_AGAIN;

import com.golubovich.elibrary.utils.Initializer;
import com.golubovich.elibrary.view.AdminMenu;
import com.golubovich.elibrary.view.ClientMenu;
import java.util.Scanner;

public class LibraryMain {

  public static void main(String[] args) {
    final String READER = "1";
    final String ADMIN = "2";
    final String EXIT = "0";

    Initializer.initialization();
    // Serialization.deserialize();

    Scanner in = new Scanner(System.in);
    System.out.println("\t Система Онлайн-Библиотека\n--------------------------------------");

    while (true) {
      System.out.println("1. Читатель \t 2. Администратор \t 0. Выход");
      String menuChoice = in.nextLine();

      switch (menuChoice) {
        case READER:
          ClientMenu.clientMenu();
          break;
        case ADMIN:
          AdminMenu.adminSignInMenu();
          break;
        case EXIT:
          // Serialization.serialize();
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }
}
