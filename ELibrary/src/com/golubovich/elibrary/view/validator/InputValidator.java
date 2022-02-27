package com.golubovich.elibrary.view.validator;

import static com.golubovich.elibrary.util.Constants.*;
import java.util.regex.Pattern;

public final class InputValidator {

    public static boolean checkPassword(String password) {
        return password != null && Pattern.matches(
                String.format(PASSWORD_PATTERN, MIN_PASSWORD_SIZE, MAX_PASSWORD_SIZE), password
        );
    }

    public static boolean checkEMail(String eMail) {
        return eMail != null && Pattern.matches(EMAIL_PATTERN, eMail);
    }

    public static boolean checkUrl(String urlAddress) {
        return urlAddress != null && Pattern.matches(URL_PATTERN, urlAddress);
    }

    public static boolean checkLetterString(String letters) {
        return letters != null && Pattern.matches(LETTERS4FIO_PATTERN, letters);
    }

    public static boolean checkAuthorString(String author) {
        return author != null && Pattern.matches(AUTHOR_PATTERN, author);
    }
}

