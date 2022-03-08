package com.golubovich.elibrary.utils;

import static com.golubovich.elibrary.utils.Constants.ADMIN_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.BOOK_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.CLIENT_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.EDUCATIONAL_MATERIAL_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.GENRE_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.LIBRARY_DB_TABLE;
import static com.golubovich.elibrary.utils.Constants.MAGAZINE_DB_TABLE;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Initializer {

  private static final Logger log = Logger.getLogger(Initializer.class);

  private Initializer() {

  }

  public static void initialization() {
    Library library = new Library("E-Library", "http://elibrary.com", "elibrary@gmail.com");
    DataSource.getInstance().setLibrary(library);

    Admin admin = new Admin("admin", "admin", "", "admin123");
    DataSource.getInstance().setAdmin(admin);

    ArrayList<Genre> genres = new ArrayList();
    genres.add(new Genre("Юмор", "смешное художественное произведение"));
    genres.add(new Genre("Роман", "художественное произведение большого объема"));
    DataSource.getInstance().setGenres(genres);

    List<Client> clients = new ArrayList();
    clients.add(new Client(
        "Ivanov",
        "Ivan",
        "Ivanovich",
        "aaaa1111",
        15,
        "a@a.a",
        new Date(),
        ClientStatus.BEGINNER)
    );
    clients.add(new Client(
        "Petrov",
        "Petr",
        "Petrovich",
        "Petr1234",
        69,
        "petr@gmail.com",
        new Date(1000000000000L),
        ClientStatus.REGULAR_READER)
    );
    DataSource.getInstance().setClients(clients);

    ArrayList<Item> books = new ArrayList();
    ArrayList<String> reviews = new ArrayList<>();
    reviews.add("good");
    books.add(new Book(
        "Хирургия",
        reviews,
        "русский",
        genres.get(0),
        "Чехов А.П.")
    );
    books.add(new Book(
        "Война и мир",
        new ArrayList(),
        "русский",
        genres.get(1),
        "Толстой Л.Н.")
    );

    ArrayList<Item> edMaterials = new ArrayList();
    edMaterials.add(new EducationalMaterial(
        "Биология 5 кл.",
        new ArrayList(),
        "русский",
        EdMaterialSubjects.BIOLOGY,
        EdMaterialType.TEXTBOOK,
        "Автор1 А.Б., Автор2 В.Г.")
    );

    ArrayList<Item> magazines = new ArrayList();
    magazines.add(new Magazine(
        "Vogue",
        new ArrayList(),
        "english",
        15,
        new Date())
    );

    Map<ItemType, ArrayList<Item>> items = new HashMap();
    items.put(ItemType.BOOK, books);
    items.put(ItemType.MAGAZINE, magazines);
    items.put(ItemType.EDUCATIONAL_MATERIAL, edMaterials);

    DataSource.getInstance().setItems(items);

    // filesDB initialization
    Initializer initializer = new Initializer();
    initializer.writeSingleObjectToFile(ADMIN_DB_TABLE, admin);
    initializer.writeSingleObjectToFile(LIBRARY_DB_TABLE, library);
    initializer.writeItemArrayToFile(GENRE_DB_TABLE, genres);
    initializer.writeItemArrayToFile(CLIENT_DB_TABLE, clients);
    initializer.writeItemArrayToFile(BOOK_DB_TABLE, books);
    initializer.writeItemArrayToFile(MAGAZINE_DB_TABLE, magazines);
    initializer.writeItemArrayToFile(EDUCATIONAL_MATERIAL_DB_TABLE, edMaterials);
  }

  public static void initializeCountFromFile() {
    int count = 0;

    try (Scanner scanner = new Scanner(new File(CLIENT_DB_TABLE))) {
      while (scanner.hasNextLine()) {
        scanner.nextLine();
        count++;
      }
    } catch (FileNotFoundException e) {
      log.error(e.getMessage());
    }

    Client.setCount(count);
  }

  private <T> void writeSingleObjectToFile(String filePath, T object) {
    try (FileWriter fw = new FileWriter(filePath, false)) {
      String jsonObjectString = JSONSerializer.toJSON(object);
      fw.write(jsonObjectString + '\n');
      fw.flush();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  private <T> void writeItemArrayToFile(String filePath, List<T> items) {
    try (BufferedWriter fw = new BufferedWriter(new FileWriter(filePath))) {
      ArrayList<String> jsonItems = new ArrayList<>();
      for (T item : items) {
        jsonItems.add(JSONSerializer.toJSON(item));
      }
      for (String s : jsonItems) {
        fw.write(s);
        fw.newLine();
      }
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}

