package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.utils.Constants.*;
import com.golubovich.elibrary.view.validator.InputValidator;
import java.util.Scanner;

public class Menu {
    protected static final String SUCCESS = "0";
    protected static final String ERROR = "1";
    protected static final String BACK = "0";
    protected static final Scanner in = new Scanner(System.in);

    protected static String makeRequestToChangeData(String requestName) {
        String surname = enterLetterString("Новая фамилия (или \"-\"): ");
        if (BACK.equals(surname)) {
            return BACK;
        }
        String name = enterLetterString("Новое имя (или \"-\"): ");
        if (BACK.equals(name)) {
            return BACK;
        }
        String patronymic = enterLetterString("Новое отчество (или \"-\"): ");
        if (BACK.equals(patronymic)) {
            return BACK;
        }
        String password = enterPassword("Новый пароль (или \"-\"): ");
        if (BACK.equals(password)) {
            return BACK;
        }
        return requestName +
                " surname=" + surname +
                " name=" + name +
                " patronymic=" + patronymic +
                " password=" + password;
    }


    protected static int enterRangedInt(String output, int min, int max) {
        if (min == 0) {
            System.out.println("0 - exit ERROR wrong use of function");
            return 0;
        }

        while(true) {
            System.out.print(output);

            try {
                int number = Integer.parseInt(in.nextLine());

                if (number == 0) {
                    return number;
                }
                if (number < min) {
                    System.out.println("Неверный ввод (" + min + "+).");
                    continue;
                }
                if (number > max) {
                    System.out.println("Неверный ввод (" + max + "-).");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println(WRONG_NUMBERS_FORMAT);
            }
        }
    }

    protected static String enterPassword(String output) {
        boolean ignoreValidation = false;

        if (output.contains("(или \"-\")")) {
            ignoreValidation = true;
        }

        while(true) {
            System.out.print(output);
            String password = in.nextLine();
            if (BACK.equals(password)) {
                return BACK;
            }

            if (ignoreValidation && password != null && password.equals("-")
                    || InputValidator.checkPassword(password)) {
                return password;
            }
            System.out.println(WRONG_PASSWORD_FORMAT);
        }
    }

    protected static String enterEMail(String output) {
        boolean ignoreValidation = false;

        if (output.contains("(или \"-\")")) {
            ignoreValidation = true;
        }

        while(true) {
            System.out.print(output);

            String eMail = in.nextLine();

            if (BACK.equals(eMail)) {
                return eMail;
            }
            if (ignoreValidation && eMail != null && eMail.equals("-") || InputValidator.checkEMail(eMail)) {
                return eMail;
            }
            System.out.println(WRONG_EMAIL_FORMAT);
        }
    }

    protected static String enterUrlAddress(String output) {
        boolean ignoreValidation = false;

        if (output.contains("(или \"-\")")) {
            ignoreValidation = true;
        }
        while (true) {
            System.out.print(output);

            String urlAddress = in.nextLine();

            if (BACK.equals(urlAddress)) {
                return BACK;
            }
            if (ignoreValidation && urlAddress != null && urlAddress.equals("-")
                    || InputValidator.checkUrl(urlAddress)) {
                return urlAddress;
            }
            System.out.println(WRONG_URL_FORMAT);
        }
    }

    protected static String enterLetterString(String output) {
        boolean ignoreValidation = false;

        if (output.contains("(или \"-\")")) {
            ignoreValidation = true;
        }
        while(true) {
            System.out.print(output);

            String letters = in.nextLine();

            if (BACK.equals(letters)) {
                return BACK;
            }
            if (ignoreValidation && letters != null && letters.equals("-")
                    || InputValidator.checkLetterString(letters)) {
                return letters;
            }
            System.out.println(WRONG_LETTERS_FORMAT);
        }
    }

    protected static String enterAuthorString(String output) {
        while(true) {
            System.out.print(output);

            String author = in.nextLine();

            if (BACK.equals(author)) {
                return BACK;
            }
            if (InputValidator.checkAuthorString(author)) {
                return author;
            }
            System.out.println(WRONG_AUTHOR_STRING_FORMAT);
        }
    }
}

