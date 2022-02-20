package com.golubovich.elibrary.view;

import com.golubovich.elibrary.view.validator.InputValidator;
import java.util.Scanner;

public class Menu {
    protected static final String SUCCESS = "0";
    protected static final String ERROR = "1";
    protected static final String BACK = "0";
    protected static final Scanner in;

    public Menu() {
    }

    protected static String makeRequestToChangeData(String requestName) {
        String surname = enterLetterString("Новая фамилия (или \"-\"): ");
        if ("0".equals(surname)) {
            return "0";
        } else {
            String name = enterLetterString("Новое имя (или \"-\"): ");
            if ("0".equals(name)) {
                return "0";
            } else {
                String patronymic = enterLetterString("Новое отчество (или \"-\"): ");
                if ("0".equals(patronymic)) {
                    return "0";
                } else {
                    String password = enterPassword("Новый пароль (или \"-\"): ");
                    if ("0".equals(password)) {
                        return "0";
                    } else {
                        String request = requestName + " surname=" + surname + " name=" + name + " patronymic=" + patronymic + " password=" + password;
                        return request;
                    }
                }
            }
        }
    }

    protected static int enterRangedInt(String output, int min, int max) {
        if (min == 0) {
            System.out.println("0 - exit ERROR wrong use of function");
            return 0;
        } else {
            while(true) {
                System.out.print(output);

                try {
                    int number = Integer.parseInt(in.nextLine());
                    if (number == 0) {
                        return number;
                    }

                    if (number < min) {
                        System.out.println("Неверный ввод (" + min + "+).");
                    } else {
                        if (number <= max) {
                            return number;
                        }

                        System.out.println("Неверный ввод (" + max + "-).");
                    }
                } catch (NumberFormatException var5) {
                    System.out.println("Неверный ввод. Только цифры.");
                }
            }
        }
    }

    protected static String enterPassword(String output) {
        boolean check = true;
        if (output.contains("(или \"-\")")) {
            check = false;
        }

        while(true) {
            System.out.print(output);
            String password = in.nextLine();
            if ("0".equals(password)) {
                return password;
            }

            if (!check && password != null && password.equals("-") || InputValidator.checkPassword(password)) {
                return password;
            }

            System.out.println("Неверный ввод. Пароль буквенно цифровой 8-16 символов.");
        }
    }

    protected static String enterEMail(String output) {
        boolean check = true;
        if (output.contains("(или \"-\")")) {
            check = false;
        }

        while(true) {
            System.out.print(output);
            String e_mail = in.nextLine();
            if ("0".equals(e_mail)) {
                return e_mail;
            }

            if (!check && e_mail != null && e_mail.equals("-") || InputValidator.checkEMail(e_mail)) {
                return e_mail;
            }

            System.out.println("Неверный ввод. Формат e-mail: АнглийскиеБуквыИЦифры@буквы.буквы");
        }
    }

    protected static String enterUrlAddress(String output) {
        boolean check = true;
        if (output.contains("(или \"-\")")) {
            check = false;
        }

        while(true) {
            System.out.print(output);
            String urlAddress = in.nextLine();
            if ("0".equals(urlAddress)) {
                return urlAddress;
            }

            if (!check && urlAddress != null && urlAddress.equals("-") || InputValidator.checkUrl(urlAddress)) {
                return urlAddress;
            }

            System.out.println("Неверный ввод. Формат url-адреса: http://АнглийскиеБуквы.буквы");
        }
    }

    protected static String enterLetterString(String output) {
        boolean check = true;
        if (output.contains("(или \"-\")")) {
            check = false;
        }

        while(true) {
            System.out.print(output);
            String letters = in.nextLine();
            if ("0".equals(letters)) {
                return letters;
            }

            if (!check && letters != null && letters.equals("-") || InputValidator.checkLetterString(letters)) {
                return letters;
            }

            System.out.println("Неверный ввод. Только буквенные символы (>1)");
        }
    }

    protected static String enterAuthorString(String output) {
        boolean var2 = true;

        while(true) {
            System.out.print(output);
            String author = in.nextLine();
            if ("0".equals(author)) {
                return author;
            }

            if (InputValidator.checkAuthorString(author)) {
                return author;
            }

            System.out.println("Неверный ввод. Только буквенные символы . и пробелы");
        }
    }

    static {
        in = new Scanner(System.in);
    }
}

