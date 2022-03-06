package com.golubovich.elibrary;

import static com.golubovich.elibrary.utils.Constants.SERIALIZATION_FILE_NAME;
import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT;

import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.utils.Initializer;
import com.golubovich.elibrary.utils.Serialization;
import com.golubovich.elibrary.view.AdminMenu;
import com.golubovich.elibrary.view.ClientMenu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;


public class LibraryMain {

    public static void main(String[] args) {
        final String READER = "1";
        final String ADMIN = "2";
        final String EXIT = "0";


        Serialization.deserialize();



        Scanner in = new Scanner(System.in);
        System.out.println("\t Система Онлайн-Библиотека\n--------------------------------------");

        while(true) {
            System.out.println("1. Читатель \t 2. Администратор \t 0. Выход");
            String menuChoice = in.nextLine();

            switch(menuChoice) {
                case READER:
                    ClientMenu.clientMenu();
                    break;
                case ADMIN:
                    AdminMenu.adminSignInMenu();
                    break;
                case EXIT:
                    Serialization.serialize();
                    return;
                default:
                    System.out.println(WRONG_INPUT);
            }
        }
    }
}
