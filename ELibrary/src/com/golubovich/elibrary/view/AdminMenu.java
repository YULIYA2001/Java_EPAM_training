package com.golubovich.elibrary.view;

import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.enums.ItemType;

public class AdminMenu extends Menu {
    public AdminMenu() {
    }

    public static void adminSignInMenu() {
        ControllerImpl controller = new ControllerImpl();

        while(true) {
            String password = enterPassword("Пароль: ");
            if ("0".equals(password)) {
                return;
            }

            String request = "authorize password=" + password;
            String response = controller.doAction(request, (Object)null);
            String var4 = response.split("\\s+")[0];
            byte var5 = -1;
            switch(var4.hashCode()) {
                case 48:
                    if (var4.equals("0")) {
                        var5 = 0;
                    }
                    break;
                case 49:
                    if (var4.equals("1")) {
                        var5 = 1;
                    }
            }

            switch(var5) {
                case 0:
                    System.out.println("Успешный вход в систему. Роль: администратор.");
                    adminMenu();
                    return;
                case 1:
                    System.out.println("Неверный пароль.");
            }
        }
    }

    private static void adminMenu() {
        String CHANGE = "1";
        String SHOW = "2";
        String var2 = "3";

        while(true) {
            System.out.println("\n1.Изменить данные (ФИО, пароль)   2.Просмотреть данные   3.Работа с библиотекой   0.Назад");
            String menuChoice = in.nextLine();
            byte var5 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var5 = 3;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var5 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var5 = 1;
                    }
                    break;
                case 51:
                    if (menuChoice.equals("3")) {
                        var5 = 2;
                    }
            }

            switch(var5) {
                case 0:
                    adminMenuChangeAdmin();
                    break;
                case 1:
                    adminMenuShowAdmin();
                    break;
                case 2:
                    adminMenuLibrary();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static void adminMenuChangeAdmin() {
        String request = makeRequestToChangeData("change-pd");
        if (!"0".equals(request)) {
            Controller controller = new ControllerImpl();
            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var5 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var5 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var5 = 1;
                    }
            }

            switch(var5) {
                case 0:
                    System.out.println("Изменения приняты.");
                    break;
                case 1:
                    System.out.println("Что-то пошло не так при изменении данных админа (Я: LibraryMenu class, adminMenuSuccessChange method)");
                    System.out.println(response);
            }

        }
    }

    private static void adminMenuShowAdmin() {
        Controller controller = new ControllerImpl();
        String request = "show-pd";
        String response = controller.doAction(request, (Object)null);
        String result = response.split("\\s+")[0];
        byte var5 = -1;
        switch(result.hashCode()) {
            case 48:
                if (result.equals("0")) {
                    var5 = 0;
                }
                break;
            case 49:
                if (result.equals("1")) {
                    var5 = 1;
                }
        }

        switch(var5) {
            case 0:
                System.out.println(response.substring(2));
                break;
            case 1:
                System.out.println(response);
        }

    }

    private static void adminMenuLibrary() {
        String CLIENTS = "1";
        String BOOKS = "2";
        String ED_MATERIALS = "3";
        String MAGAZINES = "4";
        String var4 = "5";

        while(true) {
            System.out.println("\n1.Читатели   2.Книги   3.Учебные материалы   4.Журналы   5. Данные о библиотеке   0.Назад");
            String menuChoice = in.nextLine();
            byte var7 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var7 = 5;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var7 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var7 = 1;
                    }
                    break;
                case 51:
                    if (menuChoice.equals("3")) {
                        var7 = 2;
                    }
                    break;
                case 52:
                    if (menuChoice.equals("4")) {
                        var7 = 3;
                    }
                    break;
                case 53:
                    if (menuChoice.equals("5")) {
                        var7 = 4;
                    }
            }

            switch(var7) {
                case 0:
                    adminMenuClients();
                    break;
                case 1:
                    LibraryMenu.libraryAdminMenu(ItemType.BOOK);
                    break;
                case 2:
                    LibraryMenu.libraryAdminMenu(ItemType.EDUCATIONAL_MATERIAL);
                    break;
                case 3:
                    LibraryMenu.libraryAdminMenu(ItemType.MAGAZINE);
                    break;
                case 4:
                    LibraryMenu.aboutLibrary();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }

    private static void adminMenuClients() {
        String SHOW_ALL = "1";
        String CHANGE_CLIENT_STATUS = "2";
        String var2 = "3";

        while(true) {
            System.out.println("\nРабота с клиентами:\n1.Показать   2.Назначить новый статус   3.Удалить   0.Назад");
            String menuChoice = in.nextLine();
            byte var5 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var5 = 3;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var5 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var5 = 1;
                    }
                    break;
                case 51:
                    if (menuChoice.equals("3")) {
                        var5 = 2;
                    }
            }

            switch(var5) {
                case 0:
                    showClients();
                    break;
                case 1:
                    changeClientStatus();
                    break;
                case 2:
                    deleteClient();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }

    private static void showClients() {
        Controller controller = new ControllerImpl();
        String request = "show-clients";
        String response = controller.doAction(request, (Object)null);
        String result = response.split("\\s+")[0];
        byte var5 = -1;
        switch(result.hashCode()) {
            case 48:
                if (result.equals("0")) {
                    var5 = 0;
                }
                break;
            case 49:
                if (result.equals("1")) {
                    var5 = 1;
                }
        }

        switch(var5) {
            case 0:
                System.out.println(response.substring(2));
                break;
            case 1:
                System.out.println(response);
        }

    }

    private static void changeClientStatus() {
        String BEGINNER = "1";
        String VISITOR = "2";
        String READER = "3";
        String REGULAR_READER = "4";
        String eMail = enterEMail("E-mail пользователя: ");
        if (!"0".equals(eMail)) {
            String status = "-";
            boolean stop = false;

            while(!stop) {
                System.out.println("Новый статус:  1.Начинающий   2.Поселитель   3.Читатель    4.Постоянный читатель   0.Назад");
                String menuChoice = in.nextLine();
                byte var9 = -1;
                switch(menuChoice.hashCode()) {
                    case 48:
                        if (menuChoice.equals("0")) {
                            var9 = 4;
                        }
                        break;
                    case 49:
                        if (menuChoice.equals("1")) {
                            var9 = 0;
                        }
                        break;
                    case 50:
                        if (menuChoice.equals("2")) {
                            var9 = 1;
                        }
                        break;
                    case 51:
                        if (menuChoice.equals("3")) {
                            var9 = 2;
                        }
                        break;
                    case 52:
                        if (menuChoice.equals("4")) {
                            var9 = 3;
                        }
                }

                switch(var9) {
                    case 0:
                        status = ClientStatus.BEGINNER.getName();
                        stop = true;
                        break;
                    case 1:
                        status = ClientStatus.VISITOR.getName();
                        stop = true;
                        break;
                    case 2:
                        status = ClientStatus.READER.getName();
                        stop = true;
                        break;
                    case 3:
                        status = ClientStatus.REGULAR_READER.getName();
                        stop = true;
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Неверный ввод. Попробуйте снова");
                }
            }

            Controller controller = new ControllerImpl();
            String request = "change-client-status email=" + eMail + " surname=- name=- patronymic=- password=- status=" + status;
            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var13 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var13 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var13 = 1;
                    }
            }

            switch(var13) {
                case 0:
                    System.out.println("Статус пользователя изменен");
                    break;
                case 1:
                    System.out.println(response);
            }

        }
    }

    private static void deleteClient() {
        String eMail = enterEMail("E-mail пользователя: ");
        if (!"0".equals(eMail)) {
            Controller controller = new ControllerImpl();
            String request = "delete-client email=" + eMail;
            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var6 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var6 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var6 = 1;
                    }
            }

            switch(var6) {
                case 0:
                    System.out.println("Пользователь удален");
                    break;
                case 1:
                    System.out.println(response);
            }

        }
    }
}

