package com.golubovich.elibrary.utils;

public final class Constants {
    // number constants
    public static final int MIN_PASSWORD_SIZE = 8;
    public static final int MAX_PASSWORD_SIZE = 16;
    public static final int MIN_AGE = 6;
    public static final int MAX_AGE = 110;

    // regex patterns
    public static final String PASSWORD_PATTERN = "^[a-zA-Zа-яА-Я0-9]{%d,%d}$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$";
    public static final String URL_PATTERN = "^https?://[a-zA-Z]+\\.[a-z]+$";
    public static final String LETTERS_SIMPLE_PATTERN = "^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]+$";
    public static final String LETTERS_PATTERN = "^[a-zA-Zа-яА-Я\\-\\s]+$";
    public static final String AUTHOR_PATTERN = "[a-zA-Zа-яА-Я.\\-\\s]+";

    // paths
    public static final String SERIALIZATION_FILE_NAME = "ELibrary.dat";
    public static final String RESOURCES = "src/main/resources/";
    public static final String ADMIN_DB_TABLE = RESOURCES + "DB/AdminTable.json";
    public static final String BOOK_DB_TABLE = RESOURCES + "DB/BookTable.json";
    public static final String CLIENT_DB_TABLE = RESOURCES + "DB/ClientTable.json";
    public static final String EDUCATIONAL_MATERIAL_DB_TABLE = RESOURCES + "DB/EducationalMaterialTable.json";
    public static final String GENRE_DB_TABLE = RESOURCES + "DB/GenreTable.json";
    public static final String LIBRARY_DB_TABLE = RESOURCES + "DB/LibraryTable.json";
    public static final String MAGAZINE_DB_TABLE = RESOURCES + "DB/MagazineTable.json";


    // wrong input messages
    public static final String WRONG_EMAIL_FORMAT = "Неверный ввод. Формат e-mail: АнглийскиеБуквыИЦифры@буквы.буквы";
    public static final String WRONG_PASSWORD_FORMAT = "Неверный ввод. Пароль буквенно цифровой " + MIN_PASSWORD_SIZE + "-" + MAX_PASSWORD_SIZE + " символов.";
    public static final String WRONG_URL_FORMAT = "Неверный ввод. Формат url-адреса: http[s]://АнглийскиеБуквы.буквы";
    public static final String WRONG_LETTERS_FORMAT = "Неверный ввод. Только буквенные символы (>1)";
    public static final String WRONG_AUTHOR_STRING_FORMAT = "Неверный ввод. Только буквенные символы . и пробелы";
    public static final String WRONG_NUMBERS_FORMAT ="Неверный ввод. Только цифры.";
    public static final String WRONG_INPUT_TRY_AGAIN = "Неверный ввод. Попробуйте снова";
    public static final String WRONG_INPUT = "Неверный ввод";


    // client dialog messages
    public static final String NO_CHANGES = "-";
    public static final String CONTAINS_NO_CHANGES = "(или \"-\")";
    public static final String CODE = "Код: ";
//    public static final String CODE = "Код: ";
//    public static final String CODE = "Код: ";

    public static final String NEW_SURNAME = "Новая фамилия (или \"-\"): ";
    public static final String NEW_NAME = "Новое имя (или \"-\"): ";
    public static final String NEW_PATRONYMIC = "Новое отчество (или \"-\"): ";
    public static final String NEW_PASSWORD = "Новый пароль (или \"-\"): ";

    public static final String EMPTY_MAGAZINES_LIST = "Список журналов пуст";
    public static final String EMPTY_BOOKS_LIST = "Список книг пуст";
    public static final String EMPTY_ED_MATERIALS_LIST = "Список учебных материалов пуст";
    public static final String EMPTY_GENRES_LIST = "Список жанров пуст";
    public static final String EMPTY_CLIENTS_LIST = "Нет зарегистрированных клиентов";
    public static final String WRONG_MAGAZINE_CODE = "Неверный код журнала";
    public static final String WRONG_BOOK_CODE = "Неверный код книги";
    public static final String WRONG_ED_MATERIAL_CODE = "Неверный код учебного материала";
    public static final String NO_REVIEWS = "Отзывов нет";
    public static final String REVIEWS = "Отзывы";
    public static final String CLIENT_NOT_FOUND = "Читатель не найден";


    // response string parts
    public static final String NAME_ = " name=";
    public static final String LANGUAGE_ =  " language=";
    public static final String CODE_ = " code=";
    public static final String REVIEW_ = " review=";
    public static final String EMAIL_ = " e-mail=";
    public static final String PASSWORD_ = " password=";
    //public static final String EMAIL_ = " e-mail=";



    // controller result answers
    public static final String SUCCESSFUL_GENRE_ADD = "0 Жанр успешно добавлен";
    public static final String FAIL_GENRE_ADD = "1 Ошибка при добавлении жанра";

    public static final String SUCCESSFUL_MAGAZINE_ADD = "0 Журнал успешно добавлен";
    public static final String FAIL_MAGAZINE_ADD = "1 Ошибка при добавлении журнала";

    public static final String SUCCESSFUL_BOOK_ADD = "0 Книга успешно добавлена";
    public static final String FAIL_BOOK_ADD = "1 Ошибка при добавлении книги";

    public static final String SUCCESSFUL_ED_MATERIAL_ADD = "0 Учебный материал успешно добавлен";
    public static final String FAIL_ED_MATERIAL_ADD = "1 Ошибка при добавлении учебного материала";

    public static final String SUCCESSFUL_ITEM_REVIEW_ADD = "0 Отзыв успешно добавлен";
    public static final String FAIL_ITEM_REVIEW_ADD = "1 :( Неудача при добавлении отзыва. Неверный код";

    public static final String SUCCESSFUL_DELETE = "0 Успешное удаление";
    public static final String FAIL_DELETE = "1 :( Неудача при удалении. Проверьте введенные данные";
    public static final String FAIL_ITEM_DELETE = "1 :( Неудача при удалении. Неверный код";

    public static final String SUCCESSFUL_CHANGE = "0 Изменения приняты";
    public static final String FAIL_CHANGE = "1 :( Неудача при изменении данных. Проверьте введенные данные";

    public static final String SUCCESSFUL_CLIENT_AUTHORIZATION = "0 Успешный вход в систему. Роль: читатель.";
    public static final String FAIL_CLIENT_AUTHORIZATION = "1 Пользователь не найден. Проверьте логин и пароль";

    public static final String SUCCESSFUL_ADMIN_AUTHORIZATION = "0 Успешный вход в систему. Роль: администратор.";
    public static final String FAIL_ADMIN_AUTHORIZATION = "1 Неверный пароль администратора";

    public static final String SUCCESSFUL_CLIENT_REGISTRATION = "0 Регистрация и вход в систему прошли успешно. Роль: читатель";
    public static final String FAIL_CLIENT_REGISTRATION = "1 Читатель с таким e-mail уже существует";


    public static final String SMTH_WRONG = "1 Что-то пошло не так";
    public static final String WRONG_PARAMS_COUNT = "1 Wrong params count";
    public static final String WRONG_COMMAND = "1 Wrong command name";


    // controller command string names
    public static final String ADD_MAGAZINE = "add-magazine";
    public static final String ADD_BOOK = "add-book";
    public static final String ADD_ED_MATERIAL = "add-ed_material";

    public static final String DELETE_MAGAZINE = "delete-magazine";
    public static final String DELETE_BOOK = "delete-book";
    public static final String DELETE_ED_MATERIAL = "delete-ed_material";

    public static final String SHOW_MAGAZINES = "show-magazines";
    public static final String SHOW_BOOKS = "show-books";
    public static final String SHOW_ED_MATERIALS = "show-ed_materials";

    public static final String ADD_REVIEW_MAGAZINE = "add-review-magazine";
    public static final String ADD_REVIEW_BOOK = "add-review-book";
    public static final String ADD_REVIEW_ED_MATERIAL = "add-review-ed_material";

    public static final String SHOW_REVIEWS_MAGAZINE = "show-reviews-magazine";
    public static final String SHOW_REVIEWS_BOOK = "show-reviews-book";
    public static final String SHOW_REVIEWS_ED_MATERIAL = "show-reviews-ed_material";

    public static final String REGISTER_CLIENT = "register-client";
    public static final String SHOW_CLIENTS = "show-clients";
    public static final String CHANGE_CLIENT_STATUS = "change-client-status";
    public static final String DELETE_CLIENT = "delete-client";

    public static final String SHOW_PERSONAL_DATA = "show-pd";
    public static final String CHANGE_PERSONAL_DATA = "change-pd";
    public static final String AUTHORIZE = "authorize";

    public static final String ADD_GENRE = "add-genre";
    public static final String SHOW_GENRES = "show-genres";

    public static final String CHANGE_LIBRARY = "change-library";
    public static final String SHOW_LIBRARY = "show-library";
}
