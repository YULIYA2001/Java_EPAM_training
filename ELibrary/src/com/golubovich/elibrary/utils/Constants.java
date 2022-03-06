package com.golubovich.elibrary.utils;

public final class Constants {
    public static final int MIN_PASSWORD_SIZE = 8;
    public static final int MAX_PASSWORD_SIZE = 16;
    public static final int MIN_AGE = 6;
    public static final int MAX_AGE = 110;

    public static final String PASSWORD_PATTERN = "^[a-zA-Zа-яА-Я0-9]{%d,%d}$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$";
    public static final String URL_PATTERN = "^https?://[a-zA-Z]+\\.[a-z]+$";
    public static final String LETTERS4FIO_PATTERN = "^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]+$";
    public static final String AUTHOR_PATTERN = "[a-zA-Zа-яА-Я.\\-\\s]+";



    public static final String WRONG_EMAIL_FORMAT = "Неверный ввод. Формат e-mail: АнглийскиеБуквыИЦифры@буквы.буквы";
    public static final String WRONG_PASSWORD_FORMAT = "Неверный ввод. Пароль буквенно цифровой " + MIN_PASSWORD_SIZE + "-" + MAX_PASSWORD_SIZE + " символов.";
    public static final String WRONG_URL_FORMAT = "Неверный ввод. Формат url-адреса: http[s]://АнглийскиеБуквы.буквы";
    public static final String WRONG_LETTERS_FORMAT = "Неверный ввод. Только буквенные символы (>1)";
    public static final String WRONG_AUTHOR_STRING_FORMAT = "Неверный ввод. Только буквенные символы . и пробелы";
    public static final String WRONG_NUMBERS_FORMAT ="Неверный ввод. Только цифры.";

    public static final String WRONG_INPUT = "Неверный ввод. Попробуйте снова";

}
