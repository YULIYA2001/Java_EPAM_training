package com.golubovich.elibrary.util;

import com.golubovich.elibrary.beans.Book;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.source.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {
    public Initializer() {
    }

    public static void initialization() {
        ArrayList<Genre> genres = new ArrayList();
        genres.add(new Genre("Роман", "художественное произведение большого объема"));
        genres.add(new Genre("Юмор", "смешное художественное произведение"));
        DataSource.getInstance().setGenres(genres);
        List<Client> clients = new ArrayList();
        clients.add(new Client("Ivanov", "Ivan", "Ivanovich", "aaaa1111", 15, "a@a.a", new Date(), ClientStatus.BEGINNER));
        clients.add(new Client("Petrov", "Petr", "Petrovich", "Petr1234", 69, "petr@gmail.com", new Date(1000000000000L), ClientStatus.REGULAR_READER));
        DataSource.getInstance().setClients(clients);
        Map<ItemType, List<Item>> items = new HashMap();
        List<Item> books = new ArrayList();
        List<Item> edMaterial = new ArrayList();
        List<Item> magazines = new ArrayList();
        books.add(new Book("Война и мир", new ArrayList(), "русский", (Genre)genres.get(0), "Толстой Л.Н."));
        books.add(new Book("Хирургия", new ArrayList(), "русский", (Genre)genres.get(1), "Чехов А.П."));
        edMaterial.add(new EducationalMaterial("Биология 5 кл.", new ArrayList(), "русский", EdMaterialSubjects.BIOLOGY, EdMaterialType.TEXTBOOK, "Автор1 А.Б., Автор2 В.Г."));
        magazines.add(new Magazine("Vogue", new ArrayList(), "english", 15, new Date()));
        items.put(ItemType.BOOK, books);
        items.put(ItemType.MAGAZINE, magazines);
        items.put(ItemType.EDUCATIONAL_MATERIAL, edMaterial);
        DataSource.getInstance().setItems(items);
    }
}

