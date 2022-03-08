package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.utils.Constants.CONTAINS_NO_CHANGES;
import static com.golubovich.elibrary.utils.Constants.NEW_NAME;
import static com.golubovich.elibrary.utils.Constants.NEW_PASSWORD;
import static com.golubovich.elibrary.utils.Constants.NEW_PATRONYMIC;
import static com.golubovich.elibrary.utils.Constants.NEW_SURNAME;
import static com.golubovich.elibrary.utils.Constants.NO_CHANGES;
import static com.golubovich.elibrary.utils.Constants.WRONG_AUTHOR_STRING_FORMAT;
import static com.golubovich.elibrary.utils.Constants.WRONG_EMAIL_FORMAT;
import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT;
import static com.golubovich.elibrary.utils.Constants.WRONG_LETTERS_FORMAT;
import static com.golubovich.elibrary.utils.Constants.WRONG_NUMBERS_FORMAT;
import static com.golubovich.elibrary.utils.Constants.WRONG_PASSWORD_FORMAT;
import static com.golubovich.elibrary.utils.Constants.WRONG_URL_FORMAT;

import com.golubovich.elibrary.view.validator.InputValidator;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Menu {

  protected static final String SUCCESS = "0";
  protected static final String ERROR = "1";
  protected static final String BACK = "0";
  protected static final Scanner in = new Scanner(System.in);

  private static final Logger log = Logger.getLogger(Menu.class);

  protected static String makeRequestToChangeData(String requestName) {
    String surname = enterLetterString(NEW_SURNAME, true);
    if (BACK.equals(surname)) {
      return BACK;
    }
    String name = enterLetterString(NEW_NAME, true);
    if (BACK.equals(name)) {
      return BACK;
    }
    String patronymic = enterLetterString(NEW_PATRONYMIC, true);
    if (BACK.equals(patronymic)) {
      return BACK;
    }
    String password = enterPassword(NEW_PASSWORD);
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
      log.error("0 - exit ERROR wrong use of function");
      return 0;
    }

    while (true) {
      System.out.print(output);
      try {
        int number = Integer.parseInt(in.nextLine());

        if (number == 0) {
          return number;
        }
        if (number < min) {
          System.out.println(WRONG_INPUT + " (" + min + "+).");
          continue;
        }
        if (number > max) {
          System.out.println(WRONG_INPUT + " (" + max + "-).");
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

    if (output.contains(CONTAINS_NO_CHANGES)) {
      ignoreValidation = true;
    }

    while (true) {
      System.out.print(output);
      String password = in.nextLine();
      if (BACK.equals(password)) {
        return BACK;
      }

      if (ignoreValidation && password != null && password.equals(NO_CHANGES)
          || InputValidator.checkPassword(password)) {
        return password;
      }
      System.out.println(WRONG_PASSWORD_FORMAT);
    }
  }

  protected static String enterEMail(String output) {
    boolean ignoreValidation = false;

    if (output.contains(CONTAINS_NO_CHANGES)) {
      ignoreValidation = true;
    }

    while (true) {
      System.out.print(output);

      String eMail = in.nextLine();

      if (BACK.equals(eMail)) {
        return eMail;
      }
      if (ignoreValidation && eMail != null && eMail.equals(NO_CHANGES) || InputValidator
          .checkEMail(eMail)) {
        return eMail;
      }
      System.out.println(WRONG_EMAIL_FORMAT);
    }
  }

  protected static String enterUrlAddress() {
    while (true) {
      System.out.print("Новый URL-адрес (или \"-\"): ");

      String urlAddress = in.nextLine();

      if (BACK.equals(urlAddress)) {
        return BACK;
      }
      if (urlAddress != null && urlAddress.equals(NO_CHANGES) || InputValidator
          .checkUrl(urlAddress)) {
        return urlAddress;
      }
      System.out.println(WRONG_URL_FORMAT);
    }
  }

  protected static String enterLetterString(String output, boolean isSimple) {
    boolean ignoreValidation = false;

    if (output.contains(CONTAINS_NO_CHANGES)) {
      ignoreValidation = true;
    }
    while (true) {
      System.out.print(output);

      String letters = in.nextLine();

      if (BACK.equals(letters)) {
        return BACK;
      }

      if (ignoreValidation && letters != null && letters.equals(NO_CHANGES)) {
        return letters;
      } else if (letters != null) {
        boolean validationResult;
        if (isSimple) {
          validationResult = InputValidator.checkLetterSimpleString(letters);
        } else {
          validationResult = InputValidator.checkLetterString(letters);
          letters = letters.replace(' ', '_');
        }

        if (validationResult) {
          return letters;
        }
      }

      System.out.println(WRONG_LETTERS_FORMAT);
    }
  }

  protected static String enterAuthorString() {
    while (true) {
      System.out.print("Автор: ");

      String author = in.nextLine();

      if (BACK.equals(author)) {
        return BACK;
      }
      if (InputValidator.checkAuthorString(author)) {
        return author.replace(' ', '_');
      }
      System.out.println(WRONG_AUTHOR_STRING_FORMAT);
    }
  }

  protected static void analyzeResponse(String response) {
    switch (response.split("\\s+")[0]) {
      case SUCCESS -> {
        System.out.println(response.substring(2));
        log.info(response);
      }
      case ERROR -> {
        System.out.println(response.substring(2));
        log.debug(response);
      }
    }
  }
}

