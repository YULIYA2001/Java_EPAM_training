package com.golubovich.elibrary.utils;

import static com.golubovich.elibrary.utils.Constants.SERIALIZATION_FILE_NAME;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.enums.ItemType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Serialization {
  private Serialization() {}

  public static void serialize(){

    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZATION_FILE_NAME))) {
      oos.writeObject(DataSource.getInstance().getLibrary());
      oos.writeObject(DataSource.getInstance().getAdmin());
      oos.writeObject(DataSource.getInstance().getClients());
      oos.writeObject(DataSource.getInstance().getGenres());
      oos.writeObject(DataSource.getInstance().getItems().get(ItemType.BOOK));
      oos.writeObject(DataSource.getInstance().getItems().get(ItemType.EDUCATIONAL_MATERIAL));
      oos.writeObject(DataSource.getInstance().getItems().get(ItemType.MAGAZINE));
    }
    catch(IOException ex){
      System.out.println(ex.getMessage());
    }
  }

  public static void deserialize() {
    Library library;
    Admin admin;
    ArrayList<Client> clients;
    ArrayList<Genre> genres;
    ArrayList<Item> books;
    ArrayList<Item> edMaterials;
    ArrayList<Item> magazines;

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZATION_FILE_NAME))) {
      library = (Library) ois.readObject();
      admin = (Admin) ois.readObject();
      clients =(ArrayList<Client>)ois.readObject();
      genres = (ArrayList<Genre>)ois.readObject();
      books = (ArrayList<Item>)ois.readObject();
      edMaterials =(ArrayList<Item>)ois.readObject();
      magazines =(ArrayList<Item>)ois.readObject();

      DataSource.getInstance().setLibrary(library);
      DataSource.getInstance().setAdmin(admin);
      DataSource.getInstance().setGenres(genres);
      DataSource.getInstance().setClients(clients);

      EnumMap<ItemType, ArrayList<Item>> items = new EnumMap<>(ItemType.class);
      items.put(ItemType.BOOK, books);
      items.put(ItemType.MAGAZINE, magazines);
      items.put(ItemType.EDUCATIONAL_MATERIAL, edMaterials);

      DataSource.getInstance().setItems(items);

    } catch (Exception ex) {
      Initializer.initialization();
    }
  }
}
