package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.utils.Constants.ADD_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_GENRE;
import static com.golubovich.elibrary.utils.Constants.ADD_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.CHANGE_LIBRARY;
import static com.golubovich.elibrary.utils.Constants.CODE;
import static com.golubovich.elibrary.utils.Constants.CODE_;
import static com.golubovich.elibrary.utils.Constants.DELETE_BOOK;
import static com.golubovich.elibrary.utils.Constants.DELETE_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.DELETE_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.LANGUAGE_;
import static com.golubovich.elibrary.utils.Constants.NAME_;
import static com.golubovich.elibrary.utils.Constants.REVIEW_;
import static com.golubovich.elibrary.utils.Constants.SHOW_BOOKS;
import static com.golubovich.elibrary.utils.Constants.SHOW_ED_MATERIALS;
import static com.golubovich.elibrary.utils.Constants.SHOW_GENRES;
import static com.golubovich.elibrary.utils.Constants.SHOW_LIBRARY;
import static com.golubovich.elibrary.utils.Constants.SHOW_MAGAZINES;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_BOOK;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT_TRY_AGAIN;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import java.time.LocalDate;
import org.apache.log4j.Logger;

public final class LibraryMenu extends Menu {

  private static final Logger log = Logger.getLogger(LibraryMenu.class);

  protected static void libraryAdminMenu(ItemType itemType) {
    final String ADD = "1";
    final String SHOW = "2";
    final String DELETE = "3";
    final String REVIEWS = "4";

    while (true) {
      System.out.println("\n" + itemType.getName() +
          ":\n1.Добавить   2.Показать все   3.Удалить   4.Отзывы (по коду)   0.Назад");

      switch (in.nextLine()) {
        case ADD:
          libraryMenuAddItem(itemType);
          break;
        case SHOW:
          libraryMenuShowItems(itemType);
          break;
        case DELETE:
          libraryMenuDeleteItem(itemType);
          break;
        case REVIEWS:
          libraryMenuShowReviews(itemType);
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  protected static void libraryClientMenu(ItemType itemType) {
    final String SHOW_ALL = "1";
    final String REVIEWS = "2";
    final String ADD_REVIEW = "3";

    while (true) {
      System.out.println("\n" + itemType.getName() +
          ":\n1.Показать список   2.Посмотреть отзывы (по коду)   3.Добавить отзыв(по коду)   0.Назад");
      switch (in.nextLine()) {
        case SHOW_ALL:
          libraryMenuShowItems(itemType);
          break;
        case REVIEWS:
          libraryMenuShowReviews(itemType);
          break;
        case ADD_REVIEW:
          libraryMenuAddItemReview(itemType);
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void libraryMenuAddItem(ItemType itemType) {
    String name = enterLetterString("Название: ", false);
    if (BACK.equals(name)) {
      return;
    }
    String language = enterLetterString("Язык: ", true);
    if (BACK.equals(language)) {
      return;
    }

    String request = switch (itemType) {
      case MAGAZINE -> libraryMenuAddGetRequestForMagazine(
          ADD_MAGAZINE + NAME_ + name + LANGUAGE_ + language);
      case BOOK -> libraryMenuAddGetRequestForBook(
          ADD_BOOK + NAME_ + name + LANGUAGE_ + language);
      case EDUCATIONAL_MATERIAL -> libraryMenuAddGetRequestForEdMaterial(
          ADD_ED_MATERIAL + NAME_ + name + LANGUAGE_ + language);
    };
    if (BACK.equals(request)) {
      return;
    }

    Controller controller = new ControllerImpl();
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void libraryMenuShowItems(ItemType itemType) {
    Controller controller = new ControllerImpl();
    String request = switch (itemType) {
      case MAGAZINE -> SHOW_MAGAZINES;
      case BOOK -> SHOW_BOOKS;
      case EDUCATIONAL_MATERIAL -> SHOW_ED_MATERIALS;
    };

    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void libraryMenuDeleteItem(ItemType itemType) {
    int code = enterRangedInt(CODE, 1, Item.getCodeCount());
    if (BACK.equals(Integer.toString(code))) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = switch (itemType) {
      case MAGAZINE -> DELETE_MAGAZINE + CODE_ + code;
      case BOOK -> DELETE_BOOK + CODE_ + code;
      case EDUCATIONAL_MATERIAL -> DELETE_ED_MATERIAL + CODE_ + code;
    };

    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void libraryMenuShowReviews(ItemType itemType) {
    int code = enterRangedInt(CODE, 1, Item.getCodeCount());
    if (BACK.equals(Integer.toString(code))) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = switch (itemType) {
      case MAGAZINE -> SHOW_REVIEWS_MAGAZINE + CODE_ + code;
      case BOOK -> SHOW_REVIEWS_BOOK + CODE_ + code;
      case EDUCATIONAL_MATERIAL -> SHOW_REVIEWS_ED_MATERIAL + CODE_ + code;
    };

    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void libraryMenuAddItemReview(ItemType itemType) {
    int code = enterRangedInt(CODE, 1, Item.getCodeCount());
    if (BACK.equals(Integer.toString(code))) {
      return;
    }

    System.out.print("Отзыв: ");
    String review = in.nextLine();
    if (BACK.equals(review)) {
      return;
    }
    review = review.replace(' ', '_');

    Controller controller = new ControllerImpl();
    String request = switch (itemType) {
      case MAGAZINE -> ADD_REVIEW_MAGAZINE + CODE_ + code + REVIEW_ + review;
      case BOOK -> ADD_REVIEW_BOOK + CODE_ + code + REVIEW_ + review;
      case EDUCATIONAL_MATERIAL -> ADD_REVIEW_ED_MATERIAL + CODE_ + code + REVIEW_
          + review;
    };

    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static String libraryMenuAddGetRequestForMagazine(String firstRequestPart) {
    int number = enterRangedInt("Номер: ", 1, Integer.MAX_VALUE);
    if (BACK.equals(Integer.toString(number))) {
      return BACK;
    }

    int year = enterRangedInt("Год: ", 1970, LocalDate.now().getYear());
    if (BACK.equals(Integer.toString(number))) {
      return BACK;
    }

    int month = enterRangedInt("Месяц: ", 1, 12);
    if (BACK.equals(Integer.toString(number))) {
      return BACK;
    }

    return firstRequestPart + " number=" + number + " year=" + year + " month=" + month;
  }

  private static String libraryMenuAddGetRequestForBook(String firstRequestPart) {
    String author = enterAuthorString();
    if (BACK.equals(author)) {
      return BACK;
    }

    int countNext = Genre.getCodeCount() + 1;
    String outputForChoice = "Жанр:\n" + showGenres() + "[" + countNext + "] Добавить новый\n";
    int genreCode = enterRangedInt(outputForChoice, 1, countNext);

    if (BACK.equals(Integer.toString(genreCode))) {
      return BACK;
    }
    if (genreCode == countNext && !addGenre()) {
      return BACK;
    }
    return firstRequestPart + " genre_code=" + genreCode + " author=" + author;
  }

  private static String libraryMenuAddGetRequestForEdMaterial(String firstRequestPart) {
    EdMaterialType type = chooseEdMaterialType();
    if (type == EdMaterialType.DEFAULT) {
      return BACK;
    }

    int subjectCode = chooseEdMaterialSubject();
    if (BACK.equals(Integer.toString(subjectCode))) {
      return BACK;
    }

    String author = enterAuthorString();

    return firstRequestPart +
        " subject_code=" + subjectCode +
        " type=" + type.getName() +
        " author=" + author;
  }

  private static EdMaterialType chooseEdMaterialType() {
    final String HANDBOOK = "1";
    final String METHODOLOGICAL_MANUAL = "2";
    final String TASKBOOK = "3";
    final String TEXTBOOK = "4";

    System.out.println("Тип учебного материала: 1." + EdMaterialType.HANDBOOK.getName() +
        "   2." + EdMaterialType.METHODOLOGICAL_MANUAL.getName() +
        "   3." + EdMaterialType.TASKBOOK.getName() +
        "   4." + EdMaterialType.TEXTBOOK.getName());

    while (true) {
      switch (in.nextLine()) {
        case HANDBOOK:
          return EdMaterialType.HANDBOOK;
        case METHODOLOGICAL_MANUAL:
          return EdMaterialType.METHODOLOGICAL_MANUAL;
        case TASKBOOK:
          return EdMaterialType.TASKBOOK;
        case TEXTBOOK:
          return EdMaterialType.TEXTBOOK;
        case BACK:
          return EdMaterialType.DEFAULT;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static int chooseEdMaterialSubject() {
    StringBuilder subjectChooseStr = new StringBuilder("Предмет: ");

    int count = EdMaterialSubjects.values().length;

    for (int i = 0; i < count; ++i) {
      EdMaterialSubjects subject = EdMaterialSubjects.values()[i];
      subjectChooseStr.append(subject.getCode()).append(".").append(subject.getName()).append("  ");
    }
    subjectChooseStr.append("\n");

    return enterRangedInt(String.valueOf(subjectChooseStr),
        1, EdMaterialSubjects.values().length - 1);
  }

  private static String showGenres() {
    Controller controller = new ControllerImpl();
    return controller.doAction(SHOW_GENRES).substring(2);
  }

  private static boolean addGenre() {
    String name = enterLetterString("Название: ", false);

    System.out.print("Описание: ");
    String description = in.nextLine();
    if (BACK.equals(description)) {
      return false;
    }

    Controller controller = new ControllerImpl();
    String request =
        ADD_GENRE + NAME_ + name + " description=" + description.replace(' ', '_');

    String response = controller.doAction(request);
    analyzeResponse(response);
    return true;
  }

  protected static void aboutLibrary() {
    final String SHOW = "1";
    final String CHANGE = "2";

    while (true) {
      System.out.println("\n1.Показать данные   2.Изменить данные   0.Назад");
      switch (in.nextLine()) {
        case SHOW:
          libraryShowInfo();
          break;
        case CHANGE:
          libraryChangeInfo();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void libraryChangeInfo() {
    String name = enterLetterString("Новое название (или \"-\"): ", false);
    if (BACK.equals(name)) {
      return;
    }

    String urlAddress = enterUrlAddress();
    if (BACK.equals(urlAddress)) {
      return;
    }

    String eMail = enterEMail("Новый e-mail (или \"-\"): ");
    if (BACK.equals(eMail)) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = CHANGE_LIBRARY + NAME_ + name + " url=" + urlAddress + " e-mail=" + eMail;

    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  protected static void libraryShowInfo() {
    Controller controller = new ControllerImpl();
    String response = controller.doAction(SHOW_LIBRARY);
    analyzeResponse(response);
  }
}

