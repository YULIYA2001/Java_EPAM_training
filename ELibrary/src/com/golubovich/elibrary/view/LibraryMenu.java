package com.golubovich.elibrary.view;

import static com.golubovich.elibrary.util.Constants.WRONG_INPUT;

import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.controller.api.Controller;
import com.golubovich.elibrary.controller.impl.ControllerImpl;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import java.time.LocalDate;

public final class LibraryMenu extends Menu {

    protected static void libraryAdminMenu(ItemType itemType) {
        final String ADD = "1";
        final String SHOW = "2";
        final String DELETE = "3";
        final String REVIEWS = "4";

        while(true) {
            System.out.println("\n" + itemType.getName() +
                    ":\n1.Добавить   2.Показать все   3.Удалить   4.Отзывы (по коду)   0.Назад");

            switch(in.nextLine()) {
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
                    System.out.println(WRONG_INPUT);
            }
        }
    }

    protected static void libraryClientMenu(ItemType itemType) {
        final String SHOW_ALL = "1";
        final String REVIEWS = "2";
        final String ADD_REVIEW = "3";

        while(true) {
            System.out.println("\n" + itemType.getName() +
                    ":\n1.Показать все   2.Отзывы (по коду)   3.Добавить отзыв(по коду)   0.Назад");
            switch(in.nextLine()) {
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
                    System.out.println(WRONG_INPUT);
            }
        }
    }

    private static void libraryMenuAddItem(ItemType itemType) {
        String name = enterLetterString("Название: ");
        if (BACK.equals(name)) {
            return;
        }
        String language = enterLetterString("Язык: ");
        if (BACK.equals(name)) {
            return;
        }

        String request;
        switch(itemType) {
            case MAGAZINE:
                request = libraryMenuAddGetRequestForMagazine(
                        "add-magazine name=" + name + " language=" + language);
                break;
            case BOOK:
                request = libraryMenuAddGetRequestForBook(
                        "add-book name=" + name + " language=" + language);
                break;
            case EDUCATIONAL_MATERIAL:
                request = libraryMenuAddGetRequestForEdMaterial(
                        "add-ed_material name=" + name + " language=" + language);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemType);
        }
        if (BACK.equals(request)){
            return;
        }

        Controller controller = new ControllerImpl();
        String response = controller.doAction(request);

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(itemType.getName() + " добавлен(а).\n");
                break;
            case ERROR:
                System.out.println(response);
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

        String response = controller.doAction(request);
        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }

    }

    private static void libraryMenuDeleteItem(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
        if (BACK.equals(Integer.toString(code))) {
            return;
        }

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

        String response = controller.doAction(request);

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
    }

    private static void libraryMenuShowReviews(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
        if (BACK.equals(Integer.toString(code))) {
            return;
        }

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

        String response = controller.doAction(request);

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
    }

    private static void libraryMenuAddItemReview(ItemType itemType) {
        int code = enterRangedInt("Код: ", 1, Item.getCodeCount());
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

        String response = controller.doAction(request);

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
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

        return firstRequestPart + " number=" + number + " year=" + year + " month=" + --month;
    }

    private static String libraryMenuAddGetRequestForBook(String firstRequestPart) {
        String author = enterAuthorString("Автор: ");
        if (BACK.equals(author)) {
            return BACK;
        }

        int countNext =  Genre.getCodeCount() + 1;
        String outputForChoice = "Жанр:\n" + showGenres() + countNext + ". Добавить новый\n";
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

        String author = enterAuthorString("Автор: ");

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

        while(true) {
            switch(in.nextLine()) {
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
                    System.out.println(WRONG_INPUT);
            }
        }
    }

    private static int chooseEdMaterialSubject() {
        StringBuilder subjectChooseStr = new StringBuilder("Предмет: ");

        int count = EdMaterialSubjects.values().length;

        for(int i = 0; i < count; ++i) {
            EdMaterialSubjects subject = EdMaterialSubjects.values()[i];
            subjectChooseStr.append(subject.getCode()).append(".").append(subject.getName()).append("  ");
        }
        subjectChooseStr.append("\n");

        return enterRangedInt(String.valueOf(subjectChooseStr),
                1,EdMaterialSubjects.values().length - 1);
    }

    private static String showGenres() {
        Controller controller = new ControllerImpl();
        return controller.doAction("show-genres").substring(2);
    }

    private static boolean addGenre() {
        String name = enterLetterString("Название: ");

        System.out.print("Описание: ");
        String description = in.nextLine();
        if (BACK.equals(description)) {
            return false;
        }

        Controller controller = new ControllerImpl();
        String request =
                "add-genre name=" + name + " description=" + description.replace(' ', '_');

        String response = controller.doAction(request);

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
        return true;
    }

    protected static void aboutLibrary() {
        final String SHOW = "1";
        final String CHANGE = "2";

        while(true) {
            System.out.println("\n1.Показать данные   2.Изменить данные   0.Назад");
            switch(in.nextLine()) {
                case SHOW:
                    libraryShowInfo();
                    break;
                case CHANGE:
                    libraryChangeInfo();
                    break;
                case BACK:
                    return;
                default:
                    System.out.println(WRONG_INPUT);
            }
        }
    }

    private static void libraryChangeInfo() {
        String name = enterLetterString("Новое название (или \"-\"): ");
        if (BACK.equals(name)) {
            return;
        }

        String urlAddress = enterUrlAddress("Новый URL-адрес (или \"-\"): ");
        if (BACK.equals(urlAddress)) {
            return;
        }

        String eMail = enterEMail("Новый e-mail (или \"-\"): ");
        if (BACK.equals(eMail)) {
            return;
        }

        Controller controller = new ControllerImpl();
        String request = "change-library name=" + name + " url=" + urlAddress + " e-mail=" + eMail;

        String response = controller.doAction(request);
        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
    }

    protected static void libraryShowInfo() {
        Controller controller = new ControllerImpl();
        String response = controller.doAction("show-library");

        switch(response.split("\\s+")[0]) {
            case SUCCESS:
                System.out.println(response.substring(2));
                break;
            case ERROR:
                System.out.println(response);
        }
    }
}

