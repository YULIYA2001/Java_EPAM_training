package com.golubovich.elibrary.view.validator;


import static com.golubovich.elibrary.utils.Constants.AUTHOR_PATTERN;
import static com.golubovich.elibrary.utils.Constants.EMAIL_PATTERN;
import static com.golubovich.elibrary.utils.Constants.LETTERS_PATTERN;
import static com.golubovich.elibrary.utils.Constants.LETTERS_SIMPLE_PATTERN;
import static com.golubovich.elibrary.utils.Constants.MAX_PASSWORD_SIZE;
import static com.golubovich.elibrary.utils.Constants.MIN_PASSWORD_SIZE;
import static com.golubovich.elibrary.utils.Constants.PASSWORD_PATTERN;
import static com.golubovich.elibrary.utils.Constants.URL_PATTERN;

import java.util.regex.Pattern;

public final class InputValidator {

  private InputValidator() {

  }

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

  public static boolean checkLetterSimpleString(String letters) {
    return letters != null && Pattern.matches(LETTERS_SIMPLE_PATTERN, letters);
  }

  public static boolean checkLetterString(String letters) {
    return letters != null && Pattern.matches(LETTERS_PATTERN, letters);
  }

  public static boolean checkAuthorString(String author) {
    return author != null && Pattern.matches(AUTHOR_PATTERN, author);
  }
}

