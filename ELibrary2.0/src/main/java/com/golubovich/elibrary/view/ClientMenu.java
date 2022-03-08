package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.utils.Constants.AUTHORIZE;
import static com.golubovich.elibrary.utils.Constants.CHANGE_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.DELETE_CLIENT;
import static com.golubovich.elibrary.utils.Constants.EMAIL_;
import static com.golubovich.elibrary.utils.Constants.MAX_AGE;
import static com.golubovich.elibrary.utils.Constants.MIN_AGE;
import static com.golubovich.elibrary.utils.Constants.NAME_;
import static com.golubovich.elibrary.utils.Constants.PASSWORD_;
import static com.golubovich.elibrary.utils.Constants.REGISTER_CLIENT;
import static com.golubovich.elibrary.utils.Constants.SHOW_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT_TRY_AGAIN;

import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.ItemType;
import org.apache.log4j.Logger;

public class ClientMenu extends Menu {

  private static final Logger log = Logger.getLogger(ClientMenu.class);

  public static void clientMenu() {
    final String LOG_IN = "1";
    final String SIGN_UP = "2";

    while (true) {
      System.out.println("\n1.Войти   2.Зарегистрироваться   0.Назад");

      switch (in.nextLine()) {
        case LOG_IN:
          clientMenuLogIn();
          break;
        case SIGN_UP:
          clientMenuSignUp();
          break;
        case BACK:
          return;
        default:
          System.out.println("Неверный ввод. Попробуйте снова.");
      }
    }
  }

  private static void clientMenuLogIn() {
    String eMail = enterEMail("e-mail: ");
    if (BACK.equals(eMail)) {
      return;
    }

    String password = enterPassword("Пароль: ");
    if (BACK.equals(password)) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = AUTHORIZE + EMAIL_ + eMail + PASSWORD_ + password;

    String response = controller.doAction(request);
    switch (response.split("\\s+")[0]) {
      case SUCCESS -> {
        System.out.println(response.substring(2));
        log.info(response);
        clientMenuLogInSuccess(eMail);
      }
      case ERROR -> {
        System.out.println(response.substring(2));
        log.debug(response);
      }
    }
  }

  private static void clientMenuSignUp() {
    String surname = enterLetterString("Фамилия: ", true);
    if (BACK.equals(surname)) {
      return;
    }

    String name = enterLetterString("Имя: ", true);
    if (BACK.equals(name)) {
      return;
    }

    String patronymic = enterLetterString("Отчество (или \"-\"): ", true);
    if (BACK.equals(patronymic)) {
      return;
    }

    String eMail = enterEMail("e-mail: ");
    if (BACK.equals(eMail)) {
      return;
    }

    String password = enterPassword("Пароль: ");
    if (BACK.equals(password)) {
      return;
    }

    int age = enterRangedInt("Возраст: ", MIN_AGE, MAX_AGE);
    if (BACK.equals(Integer.toString(age))) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = REGISTER_CLIENT + " surname=" + surname + NAME_ + name +
        " patronymic=" + patronymic + PASSWORD_ + password +
        " age=" + age + EMAIL_ + eMail;

    String response = controller.doAction(request);
    switch (response.split("\\s+")[0]) {
      case SUCCESS -> {
        System.out.println(response.substring(2));
        log.info(response);
        clientMenuLogInSuccess(eMail);
      }
      case ERROR -> {
        System.out.println(response.substring(2));
        log.debug(response);
      }
    }
  }

  private static void clientMenuLogInSuccess(String eMail) {
    final String CHANGE = "1";
    final String SHOW = "2";
    final String DELETE_CLIENT = "3";
    final String TO_LIBRARY = "4";

    while (true) {
      System.out.println("\n1.Изменить ПД   2.Просмотреть ПД" +
          "   3.Удалить регистрацию   4.В библиотеку   0.Назад");

      switch (in.nextLine()) {
        case CHANGE:
          clientMenuChange(eMail);
          break;
        case SHOW:
          clientMenuShow(eMail);
          break;
        case DELETE_CLIENT:
          clientMenuDelete(eMail);
          return;
        case TO_LIBRARY:
          clientMenuLibrary();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void clientMenuChange(String eMail) {
    String request = makeRequestToChangeData(CHANGE_PERSONAL_DATA + EMAIL_ + eMail);
    if (BACK.equals(request)) {
      return;
    }
    request = request + " status=-";

    Controller controller = new ControllerImpl();
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void clientMenuShow(String eMail) {
    Controller controller = new ControllerImpl();
    String request = SHOW_PERSONAL_DATA + EMAIL_ + eMail;
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void clientMenuDelete(String eMail) {
    Controller controller = new ControllerImpl();
    String request = DELETE_CLIENT + EMAIL_ + eMail;
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void clientMenuLibrary() {
    final String BOOKS = "1";
    final String ED_MATERIALS = "2";
    final String MAGAZINES = "3";
    final String ABOUT_LIBRARY = "4";

    while (true) {
      System.out.println("\n1.Книги   2.Учебные материалы   3.Журналы   4.О библиотеке   0.Назад");
      switch (in.nextLine()) {
        case BOOKS:
          LibraryMenu.libraryClientMenu(ItemType.BOOK);
          break;
        case ED_MATERIALS:
          LibraryMenu.libraryClientMenu(ItemType.EDUCATIONAL_MATERIAL);
          break;
        case MAGAZINES:
          LibraryMenu.libraryClientMenu(ItemType.MAGAZINE);
          break;
        case ABOUT_LIBRARY:
          LibraryMenu.libraryShowInfo();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }
}

