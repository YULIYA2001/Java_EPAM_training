package com.golubovich.elibrary.utils;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {

    public static void initialization() {
        DataSource.getInstance().setLibrary(
            new Library("E-Library", "http://elibrary.com", "e-library@gmail.com")
        );
        DataSource.getInstance().setAdmin(
            new Admin("admin", "admin", "", "admin123")
        );

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
        books.add(new Book(
            "Хирургия",
            Collections.singletonList("good"),
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
    }
}

