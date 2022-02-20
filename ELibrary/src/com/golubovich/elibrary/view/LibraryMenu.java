package com.golubovich.elibrary.view;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import java.io.PrintStream;
import java.time.LocalDate;

public final class LibraryMenu extends Menu {
    public LibraryMenu() {
    }

    protected static void libraryAdminMenu(ItemType itemType) {
        String ADD = "1";
        String SHOW = "2";
        String DELETE = "3";
        String var4 = "4";

        while(true) {
            System.out.println("\n" + itemType.getName() + ":\n1.Добавить   2.Показать все   3.Удалить   4.Отзывы (по коду)   0.Назад");
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
                    libraryMenuAddItem(itemType);
                    break;
                case 1:
                    libraryMenuShowItems(itemType);
                    break;
                case 2:
                    libraryMenuDeleteItem(itemType);
                    break;
                case 3:
                    libraryMenuShowReviews(itemType);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }

    protected static void libraryClientMenu(ItemType itemType) {
        String SHOW = "1";
        String REVIEWS = "2";
        String var3 = "3";

        while(true) {
            System.out.println("\n" + itemType.getName() + ":\n1.Показать все   2.Отзывы (по коду)   3.Добавить отзыв(по коду)   0.Назад");
            String menuChoice = in.nextLine();
            byte var6 = -1;
            switch(menuChoice.hashCode()) {
                case 48:
                    if (menuChoice.equals("0")) {
                        var6 = 3;
                    }
                    break;
                case 49:
                    if (menuChoice.equals("1")) {
                        var6 = 0;
                    }
                    break;
                case 50:
                    if (menuChoice.equals("2")) {
                        var6 = 1;
                    }
                    break;
                case 51:
                    if (menuChoice.equals("3")) {
                        var6 = 2;
                    }
            }

            switch(var6) {
                case 0:
                    libraryMenuShowItems(itemType);
                    break;
                case 1:
                    libraryMenuShowReviews(itemType);
                    break;
                case 2:
                    libraryMenuAddItemReview(itemType);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }

    private static void libraryMenuAddItem(ItemType itemType) {
        String name = enterLetterString("Название: ");
        if (!"0".equals(name)) {
            String language = enterLetterString("Язык: ");
            if (!"0".equals(language)) {
                Controller controller = new ControllerImpl();
                String request;
                switch(itemType) {
                    case MAGAZINE:
                        request = libraryMenuAddGetRequestForMagazine("add-magazine name=" + name + " language=" + language);
                        break;
                    case BOOK:
                        request = libraryMenuAddGetRequestForBook("add-book name=" + name + " language=" + language);
                        break;
                    case EDUCATIONAL_MATERIAL:
                        request = libraryMenuAddGetRequestForEdMaterial("add-ed_material name=" + name + " language=" + language);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemType);
                }

                if (!"0".equals(request)) {
                    String response = controller.doAction(request, (Object)null);
                    String var6 = response.split("\\s+")[0];
                    byte var7 = -1;
                    switch(var6.hashCode()) {
                        case 48:
                            if (var6.equals("0")) {
                                var7 = 0;
                            }
                            break;
                        case 49:
                            if (var6.equals("1")) {
                                var7 = 1;
                            }
                    }

                    switch(var7) {
                        case 0:
                            System.out.println(itemType.getName() + " добавлен(а).\n");
                            break;
                        case 1:
                            System.out.println(response);
                    }

                }
            }
        }
    }

    private static void libraryMenuShowItems(ItemType itemType) {
        Controller controller = new ControllerImpl();
        String request;
        switch(itemType) {
            case MAGAZINE:
                request = "show-magazines";
                break;
            case BOOK:
                request = "show-books";
                break;
            case EDUCATIONAL_MATERIAL:
                request = "show-ed_materials";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemType);
        }

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

    private static void libraryMenuDeleteItem(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
        if (!"0".equals(Integer.toString(code))) {
            Controller controller = new ControllerImpl();
            String request;
            switch(itemType) {
                case MAGAZINE:
                    request = "delete-magazine code=" + code;
                    break;
                case BOOK:
                    request = "delete-book code=" + code;
                    break;
                case EDUCATIONAL_MATERIAL:
                    request = "delete-ed_material code=" + code;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + itemType);
            }

            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var7 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var7 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var7 = 1;
                    }
            }

            switch(var7) {
                case 0:
                    System.out.println(response.substring(2));
                    break;
                case 1:
                    System.out.println(response);
            }

        }
    }

    private static void libraryMenuShowReviews(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
        if (!"0".equals(Integer.toString(code))) {
            Controller controller = new ControllerImpl();
            String request;
            switch(itemType) {
                case MAGAZINE:
                    request = "show-reviews-magazine code=" + code;
                    break;
                case BOOK:
                    request = "show-reviews-book code=" + code;
                    break;
                case EDUCATIONAL_MATERIAL:
                    request = "show-reviews-ed_material code=" + code;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + itemType);
            }

            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var7 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var7 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var7 = 1;
                    }
            }

            switch(var7) {
                case 0:
                    System.out.println(response.substring(2));
                    break;
                case 1:
                    System.out.println(response);
            }

        }
    }

    private static void libraryMenuAddItemReview(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
        if (!"0".equals(Integer.toString(code))) {
            System.out.print("Отзыв: ");
            String review = in.nextLine();
            if (!"0".equals(review)) {
                review = review.replace(' ', '_');
                Controller controller = new ControllerImpl();
                String request;
                switch(itemType) {
                    case MAGAZINE:
                        request = "add-review-magazine code=" + code + " review=" + review;
                        break;
                    case BOOK:
                        request = "add-review-book code=" + code + " review=" + review;
                        break;
                    case EDUCATIONAL_MATERIAL:
                        request = "add-review-ed_material code=" + code + " review=" + review;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemType);
                }

                String response = controller.doAction(request, (Object)null);
                String result = response.split("\\s+")[0];
                byte var8 = -1;
                switch(result.hashCode()) {
                    case 48:
                        if (result.equals("0")) {
                            var8 = 0;
                        }
                        break;
                    case 49:
                        if (result.equals("1")) {
                            var8 = 1;
                        }
                }

                switch(var8) {
                    case 0:
                        System.out.println(response.substring(2));
                        break;
                    case 1:
                        System.out.println(response);
                }

            }
        }
    }

    private static String libraryMenuAddGetRequestForMagazine(String firstRequestPart) {
        int number = enterRangedInt("Номер: ", 1, 2147483647);
        if ("0".equals(Integer.toString(number))) {
            return "0";
        } else {
            int year = enterRangedInt("Год: ", 1970, LocalDate.now().getYear());
            if ("0".equals(Integer.toString(number))) {
                return "0";
            } else {
                int month = enterRangedInt("Месяц: ", 1, 12);
                if ("0".equals(Integer.toString(number))) {
                    return "0";
                } else {
                    --month;
                    return firstRequestPart + " number=" + number + " year=" + year + " month=" + month;
                }
            }
        }
    }

    private static String libraryMenuAddGetRequestForBook(String firstRequestPart) {
        int ADD_GENRE = Genre.getCodeCount() + 1;
        String author = enterAuthorString("Автор: ");
        if ("0".equals(author)) {
            return "0";
        } else {
            String var10000 = showGenres();
            String outputForChoice = "Жанр:\n" + var10000 + (Genre.getCodeCount() + 1) + ". Добавить новый\n";
            int genreCode = enterRangedInt(outputForChoice, 1, Genre.getCodeCount() + 1);
            if ("0".equals(Integer.toString(genreCode))) {
                return "0";
            } else {
                return genreCode == ADD_GENRE && !addGenre() ? "0" : firstRequestPart + " genre_code=" + genreCode + " author=" + author;
            }
        }
    }

    private static String libraryMenuAddGetRequestForEdMaterial(String firstRequestPart) {
        EdMaterialType type = chooseEdMaterialType();
        if (type == EdMaterialType.DEFAULT) {
            return "0";
        } else {
            int subjectCode = chooseEdMaterialSubject();
            if ("0".equals(Integer.toString(subjectCode))) {
                return "0";
            } else {
                String author = enterAuthorString("Автор: ");
                return firstRequestPart + " subject_code=" + subjectCode + " type=" + type.getName() + " author=" + author;
            }
        }
    }

    private static EdMaterialType chooseEdMaterialType() {
        String HANDBOOK = "1";
        String METHODOLOGICAL_MANUAL = "2";
        String TASKBOOK = "3";
        String TEXTBOOK = "4";
        PrintStream var10000 = System.out;
        String var10001 = EdMaterialType.HANDBOOK.getName();
        var10000.println("Тип учебного материала: 1." + var10001 + "   2." + EdMaterialType.METHODOLOGICAL_MANUAL.getName() + "   3." + EdMaterialType.TASKBOOK.getName() + "   4." + EdMaterialType.TEXTBOOK.getName());

        while(true) {
            String var5 = in.nextLine();
            byte var6 = -1;
            switch(var5.hashCode()) {
                case 48:
                    if (var5.equals("0")) {
                        var6 = 4;
                    }
                    break;
                case 49:
                    if (var5.equals("1")) {
                        var6 = 0;
                    }
                    break;
                case 50:
                    if (var5.equals("2")) {
                        var6 = 1;
                    }
                    break;
                case 51:
                    if (var5.equals("3")) {
                        var6 = 2;
                    }
                    break;
                case 52:
                    if (var5.equals("4")) {
                        var6 = 3;
                    }
            }

            switch(var6) {
                case 0:
                    return EdMaterialType.HANDBOOK;
                case 1:
                    return EdMaterialType.METHODOLOGICAL_MANUAL;
                case 2:
                    return EdMaterialType.TASKBOOK;
                case 3:
                    return EdMaterialType.TEXTBOOK;
                case 4:
                    return EdMaterialType.DEFAULT;
                default:
                    System.out.println("Неверный ввод (требуется число)");
            }
        }
    }

    private static int chooseEdMaterialSubject() {
        StringBuilder subjectChooseStr = new StringBuilder("Предмет: ");
        EdMaterialSubjects[] var1 = EdMaterialSubjects.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EdMaterialSubjects subjects = var1[var3];
            subjectChooseStr.append(subjects.getCode()).append(".").append(subjects.getName()).append("  ");
        }

        subjectChooseStr.append("\n");
        int subjectCode = enterRangedInt(String.valueOf(subjectChooseStr), 1, EdMaterialSubjects.values().length - 1);
        return subjectCode;
    }

    private static String showGenres() {
        Controller controller = new ControllerImpl();
        return controller.doAction("show-genres", (Object)null).substring(2);
    }

    private static boolean addGenre() {
        String name = enterLetterString("Название: ");
        System.out.print("Описание: ");
        String description = in.nextLine();
        if ("0".equals(description)) {
            return false;
        } else {
            Controller controller = new ControllerImpl();
            String request = "add-genre name=" + name + " description=" + description.replace(' ', '_');
            String response = controller.doAction(request, (Object)null);
            String result = response.split("\\s+")[0];
            byte var7 = -1;
            switch(result.hashCode()) {
                case 48:
                    if (result.equals("0")) {
                        var7 = 0;
                    }
                    break;
                case 49:
                    if (result.equals("1")) {
                        var7 = 1;
                    }
            }

            switch(var7) {
                case 0:
                    System.out.println(response.substring(2));
                    break;
                case 1:
                    System.out.println(response);
            }

            return true;
        }
    }

    protected static void aboutLibrary() {
        String SHOW = "1";
        String var1 = "2";

        while(true) {
            System.out.println("\n1.Показать данные   2.Изменить данные   0.Назад");
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
                    libraryShowInfo();
                    break;
                case 1:
                    libraryChangeInfo();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова");
            }
        }
    }

    private static void libraryChangeInfo() {
        String name = enterLetterString("Новое название (или \"-\"): ");
        if (!"0".equals(name)) {
            String urlAddress = enterUrlAddress("Новый URL-адрес (или \"-\"): ");
            if (!"0".equals(name)) {
                String eMail = enterEMail("Новый e-mail (или \"-\"): ");
                if (!"0".equals(name)) {
                    Controller controller = new ControllerImpl();
                    String request = "change-library name=" + name + " url=" + urlAddress + " e-mail=" + eMail;
                    String response = controller.doAction(request, (Object)null);
                    String result = response.split("\\s+")[0];
                    byte var8 = -1;
                    switch(result.hashCode()) {
                        case 48:
                            if (result.equals("0")) {
                                var8 = 0;
                            }
                            break;
                        case 49:
                            if (result.equals("1")) {
                                var8 = 1;
                            }
                    }

                    switch(var8) {
                        case 0:
                            System.out.println(response.substring(2));
                            break;
                        case 1:
                            System.out.println(response);
                    }

                }
            }
        }
    }

    protected static void libraryShowInfo() {
        Controller controller = new ControllerImpl();
        String response = controller.doAction("show-library", (Object)null);
        String result = response.split("\\s+")[0];
        byte var4 = -1;
        switch(result.hashCode()) {
            case 48:
                if (result.equals("0")) {
                    var4 = 0;
                }
                break;
            case 49:
                if (result.equals("1")) {
                    var4 = 1;
                }
        }

        switch(var4) {
            case 0:
                System.out.println(response.substring(2));
                break;
            case 1:
                System.out.println(response);
        }

    }
}

