package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.utils.Constants.AUTHORIZE;
import static com.golubovich.elibrary.utils.Constants.CHANGE_CLIENT_STATUS;
import static com.golubovich.elibrary.utils.Constants.CHANGE_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.DELETE_CLIENT;
import static com.golubovich.elibrary.utils.Constants.EMAIL_;
import static com.golubovich.elibrary.utils.Constants.PASSWORD_;
import static com.golubovich.elibrary.utils.Constants.SHOW_CLIENTS;
import static com.golubovich.elibrary.utils.Constants.SHOW_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.WRONG_INPUT_TRY_AGAIN;

import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.enums.ItemType;
import org.apache.log4j.Logger;

public class AdminMenu extends Menu {

  private static final Logger log = Logger.getLogger(AdminMenu.class);

  public static void adminSignInMenu() {
    ControllerImpl controller = new ControllerImpl();

    while (true) {
      String password = enterPassword("Пароль: ");
      if (BACK.equals(password)) {
        return;
      }

      String request = AUTHORIZE + PASSWORD_ + password;
      String response = controller.doAction(request);

      switch (response.split("\\s+")[0]) {
        case SUCCESS -> {
          System.out.println(response.substring(2));
          log.info(response);
          adminMenu();
          return;
        }
        case ERROR -> {
          System.out.println(response.substring(2));
          log.debug(response);
        }
      }
    }
  }

  private static void adminMenu() {
    final String CHANGE = "1";
    final String SHOW = "2";
    final String TO_LIBRARY = "3";

    while (true) {
      System.out.println("\n1.Изменить данные (ФИО, пароль)   2.Просмотреть данные" +
          "   3.Работа с библиотекой   0.Назад");

      switch (in.nextLine()) {
        case CHANGE:
          adminMenuChangeAdmin();
          break;
        case SHOW:
          adminMenuShowAdmin();
          break;
        case TO_LIBRARY:
          adminMenuLibrary();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void adminMenuChangeAdmin() {
    String request = makeRequestToChangeData(CHANGE_PERSONAL_DATA);
    if (BACK.equals(request)) {
      return;
    }

    Controller controller = new ControllerImpl();
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void adminMenuShowAdmin() {
    Controller controller = new ControllerImpl();
    String response = controller.doAction(SHOW_PERSONAL_DATA);
    analyzeResponse(response);
  }

  private static void adminMenuLibrary() {
    final String CLIENTS = "1";
    final String BOOKS = "2";
    final String ED_MATERIALS = "3";
    final String MAGAZINES = "4";
    final String LIBRARY = "5";

    while (true) {
      System.out.println("\n1.Читатели   2.Книги   3.Учебные материалы   4.Журналы" +
          "   5. Данные о библиотеке   0.Назад");

      switch (in.nextLine()) {
        case CLIENTS:
          adminMenuClients();
          break;
        case BOOKS:
          LibraryMenu.libraryAdminMenu(ItemType.BOOK);
          break;
        case ED_MATERIALS:
          LibraryMenu.libraryAdminMenu(ItemType.EDUCATIONAL_MATERIAL);
          break;
        case MAGAZINES:
          LibraryMenu.libraryAdminMenu(ItemType.MAGAZINE);
          break;
        case LIBRARY:
          LibraryMenu.aboutLibrary();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void adminMenuClients() {
    final String SHOW_ALL = "1";
    final String CHANGE_CLIENT_STATUS = "2";
    final String DELETE = "3";

    while (true) {
      System.out.println("\nРабота с клиентами:\n1.Показать всех   2.Назначить новый статус" +
          "   3.Удалить   0.Назад");

      switch (in.nextLine()) {
        case SHOW_ALL:
          showClients();
          break;
        case CHANGE_CLIENT_STATUS:
          changeClientStatus();
          break;
        case DELETE:
          deleteClient();
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }
  }

  private static void showClients() {
    Controller controller = new ControllerImpl();
    String response = controller.doAction(SHOW_CLIENTS);
    analyzeResponse(response);
  }

  private static void changeClientStatus() {
    final String BEGINNER = "1";
    final String VISITOR = "2";
    final String READER = "3";
    final String REGULAR_READER = "4";

    String eMail = enterEMail("E-mail пользователя: ");
    if (BEGINNER.equals(eMail)) {
      return;
    }

    String status = "-";
    boolean stop = false;

    while (!stop) {
      System.out.println("Новый статус:  1.Начинающий   2.Поселитель   3.Читатель" +
          "    4.Постоянный читатель   0.Назад");

      switch (in.nextLine()) {
        case BEGINNER:
          status = ClientStatus.BEGINNER.getName();
          stop = true;
          break;
        case VISITOR:
          status = ClientStatus.VISITOR.getName();
          stop = true;
          break;
        case READER:
          status = ClientStatus.READER.getName();
          stop = true;
          break;
        case REGULAR_READER:
          status = ClientStatus.REGULAR_READER.getName();
          stop = true;
          break;
        case BACK:
          return;
        default:
          System.out.println(WRONG_INPUT_TRY_AGAIN);
      }
    }

    Controller controller = new ControllerImpl();
    String request = CHANGE_CLIENT_STATUS + EMAIL_ + eMail +
        " surname=- name=- patronymic=- password=- status=" + status;
    String response = controller.doAction(request);
    analyzeResponse(response);
  }

  private static void deleteClient() {
    String eMail = enterEMail("E-mail пользователя: ");
    if (BACK.equals(eMail)) {
      return;
    }

    Controller controller = new ControllerImpl();
    String request = DELETE_CLIENT + EMAIL_ + eMail;
    String response = controller.doAction(request);
    analyzeResponse(response);
  }
}

