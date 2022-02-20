package com.golubovich.elibrary.view;

import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.ItemType;

public class ClientMenu extends Menu {
    public ClientMenu() {
    }

    public static void clientMenu() {
        String LOG_IN = "1";
        String var1 = "2";

        while(true) {
            System.out.println("\n1.Войти   2.Зарегистрироваться   0.Назад");
            String menuChoice = in.nextLine();
            byte var4 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var4 = 2;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var4 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var4 = 1;
                    }
            }

            switch(var4) {
                case 0:
                    clientMenuLogIn();
                    break;
                case 1:
                    clientMenuSignUp();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static void clientMenuLogIn() {
        String e_mail = enterEMail("e-mail: ");
        if (!"0".equals(e_mail)) {
            String password = enterPassword("Пароль: ");
            if (!"0".equals(password)) {
                Controller controller = new ControllerImpl();
                String request = "authorize e-mail=" + e_mail + " password=" + password;
                String response = controller.doAction(request, (Object)null);
                String var5 = response.split("\\s+")[0];
                byte var6 = -1;
                switch(var5.hashCode()) {
                    case 48:
                        if (var5.equals("0")) {
                            var6 = 0;
                        }
                        break;
                    case 49:
                        if (var5.equals("1")) {
                            var6 = 1;
                        }
                }

                switch(var6) {
                    case 0:
                        System.out.println("Успешный вход в систему. Роль: читатель.");
                        clientMenuLogInSuccess(e_mail);
                        break;
                    case 1:
                        System.out.println("Пользователь не найден или Неверный пароль.");
                }

            }
        }
    }

    private static void clientMenuSignUp() {
        String surname = enterLetterString("Фамилия: ");
        if (!"0".equals(surname)) {
            String name = enterLetterString("Имя: ");
            if (!"0".equals(name)) {
                String patronymic = enterLetterString("Отчество (или \"-\"): ");
                if (!"0".equals(patronymic)) {
                    String e_mail = enterEMail("e-mail: ");
                    if (!"0".equals(e_mail)) {
                        String password = enterPassword("Пароль: ");
                        if (!"0".equals(password)) {
                            int age = enterRangedInt("Возраст: ", 6, 110);
                            if (!"0".equals(Integer.toString(age))) {
                                Controller controller = new ControllerImpl();
                                String request = "register-client surname=" + surname + " name=" + name + " patronymic=" + patronymic + " password=" + password + " age=" + Integer.toString(age) + " e-mail=" + e_mail;
                                String response = controller.doAction(request, (Object)null);
                                String var9 = response.split("\\s+")[0];
                                byte var10 = -1;
                                switch(var9.hashCode()) {
                                    case 48:
                                        if (var9.equals("0")) {
                                            var10 = 0;
                                        }
                                        break;
                                    case 49:
                                        if (var9.equals("1")) {
                                            var10 = 1;
                                        }
                                }

                                switch(var10) {
                                    case 0:
                                        System.out.println("Регистрация и вход в систему прошли успешно. Роль: читатель");
                                        clientMenuLogInSuccess(e_mail);
                                        break;
                                    case 1:
                                        System.out.println(response);
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private static void clientMenuLogInSuccess(String e_mail) {
        String CHANGE = "1";
        String SHOW = "2";
        String DELETE_CLIENT = "3";
        String var4 = "4";

        while(true) {
            System.out.println("\n1.Изменить данные (ФИО, пароль)   2.Просмотреть данные   3.Удалить регистрацию   4.В библиотеку   0.Назад");
            String menuChoice = in.nextLine();
            byte var7 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var7 = 4;
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
            }

            switch(var7) {
                case 0:
                    clientMenuChange(e_mail);
                    break;
                case 1:
                    clientMenuShow(e_mail);
                    break;
                case 2:
                    clientMenuDelete(e_mail);
                    return;
                case 3:
                    clientMenuLibrary();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static void clientMenuChange(String e_mail) {
        String request = makeRequestToChangeData("change-pd e-mail=" + e_mail);
        if (!"0".equals(request)) {
            request = request + " status=-";
            Controller controller = new ControllerImpl();
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
                    System.out.println("Изменения приняты.");
                    break;
                case 1:
                    System.out.println("Что-то пошло не так при изменении данных клиента (Я: LibraryMenu class, clientMenuChange method)");
                    System.out.println(response);
            }

        }
    }

    private static void clientMenuShow(String e_mail) {
        Controller controller = new ControllerImpl();
        String request = "show-pd e-mail=" + e_mail;
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
                System.out.println(response.substring(2));
                break;
            case 1:
                System.out.println(response);
        }

    }

    private static void clientMenuDelete(String eMail) {
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
                System.out.println("Пользователь удален\n");
                break;
            case 1:
                System.out.println(response);
        }

    }

    private static void clientMenuLibrary() {
        String CLIENTS = "1";
        String BOOKS = "1";
        String ED_MATERIALS = "2";
        String MAGAZINES = "3";
        String var4 = "4";

        while(true) {
            System.out.println("\n1.Книги   2.Учебные материалы   3.Журналы   4.О библиотеке   0.Назад");
            String menuChoice = in.nextLine();
            byte var7 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var7 = 4;
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
            }

            switch(var7) {
                case 0:
                    LibraryMenu.libraryClientMenu(ItemType.BOOK);
                    break;
                case 1:
                    LibraryMenu.libraryClientMenu(ItemType.EDUCATIONAL_MATERIAL);
                    break;
                case 2:
                    LibraryMenu.libraryClientMenu(ItemType.MAGAZINE);
                    break;
                case 3:
                    LibraryMenu.libraryShowInfo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }
}

