package com.golubovich.elibrary.utils;

import com.golubovich.elibrary.beans.Admin;
import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.beans.Genre;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Library;
import com.golubovich.elibrary.enums.ItemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>Singleton DataSource class</b>
 * <p>
 *   DataSource class with fields <b>library</b>, <b>admin</b>, <b>clients</b>,
 *   <b>genres</b>, <b>items</b>
 *   Emulates database in program.
 *   Saves all beans as objects and arrays
 *
 * @author Yulia Golubovich
 * @version 1.0
 * @since 08.03.2022
 */
public class DataSource {

  /**
   * Field library contains general information about library:
   *  name, e-mail, url-address
   * @see Library
   */
  private Library library;
  /**
   * Field admin contains information about site administrator:
   *  name,surname, [patronymic], password
   * @see Admin
   */
  private Admin admin;
  /**
   * Field clients contains information about readers:
   *  name,surname, [patronymic], password, e-mail, age etc.
   * @see Client
   */
  private List<Client> clients;
  /**
   * Field genres contains information about book genres:
   *  name and description
   * @see Genre
   */
  private List<Genre> genres;
  /**
   * Field items contains information about all literature:
   * books, educational materials, magazines.
   * It saves information as map, where key is an enum element which
   * displays literature type
   * @see ItemType
   * @see com.golubovich.elibrary.beans.Book
   * @see com.golubovich.elibrary.beans.EducationalMaterial
   * @see com.golubovich.elibrary.beans.Magazine
   */
  private Map<ItemType, ArrayList<Item>> items;
  /**
   * Singleton field to avoid multiple object creations
   */
  private static final DataSource instance = new DataSource();


  /**
   * Singleton method to take a single object of class
   */
  public static DataSource getInstance() {
    return instance;
  }

  public List<Genre> getGenres() {
    return this.genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Library getLibrary() {
    return this.library;
  }

  public void setLibrary(Library library) {
    this.library = library;
  }

  public Admin getAdmin() {
    return this.admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public List<Client> getClients() {
    return this.clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  public Map<ItemType, ArrayList<Item>> getItems() {
    return this.items;
  }

  public void setItems(Map<ItemType, ArrayList<Item>> items) {
    this.items = items;
  }
}

