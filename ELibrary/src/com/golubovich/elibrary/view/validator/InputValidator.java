package com.golubovich.elibrary.view.validator;

import java.util.regex.Pattern;

public final class InputValidator {
    public InputValidator() {
    }

    public static boolean checkPassword(String password) {
        return password != null && Pattern.matches(String.format("^[a-zA-Zа-яА-Я0-9]{%d,%d}$", 8, 16), password);
    }

    public static boolean checkEMail(String e_mail) {
        return e_mail != null && Pattern.matches("^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$", e_mail);
    }

    public static boolean checkUrl(String urlAddress) {
        return urlAddress != null && Pattern.matches("^http://[a-zA-Z]+\\.[a-z]+$", urlAddress);
    }

    public static boolean checkLetterString(String letters) {
        return letters != null && Pattern.matches("^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]+$", letters);
    }

    public static boolean checkAuthorString(String author) {
        return author != null && Pattern.matches("[a-zA-Zа-яА-Я.\\-\\s]+", author);
    }
}

