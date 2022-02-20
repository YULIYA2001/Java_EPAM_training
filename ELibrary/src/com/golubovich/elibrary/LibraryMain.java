package com.golubovich.elibrary;

import com.golubovich.elibrary.util.Initializer;
import com.golubovich.elibrary.view.AdminMenu;
import com.golubovich.elibrary.view.ClientMenu;
import java.util.Scanner;

public class LibraryMain {
    public LibraryMain() {
    }

    public static void main(String[] args) {
        String READER = "1";
        String ADMIN = "2";
        String EXIT = "0";
        Initializer.initialization();
        Scanner in = new Scanner(System.in);
        System.out.println("\t Система Онлайн-Библиотека\n--------------------------------------");

        while(true) {
            System.out.println("1. Читатель \t 2. Администратор \t 0. Выход");
            String menuChoice = in.nextLine();
            byte var7 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var7 = 2;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var7 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var7 = 1;
                    }
            }

            switch(var7) {
                case 0:
                    ClientMenu.clientMenu();
                    break;
                case 1:
                    AdminMenu.adminSignInMenu();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }
}
