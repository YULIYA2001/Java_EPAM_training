package com.golubovich.elibrary.controller;

import static com.golubovich.elibrary.utils.Constants.ADD_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_GENRE;
import static com.golubovich.elibrary.utils.Constants.ADD_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.AUTHORIZE;
import static com.golubovich.elibrary.utils.Constants.CHANGE_CLIENT_STATUS;
import static com.golubovich.elibrary.utils.Constants.CHANGE_LIBRARY;
import static com.golubovich.elibrary.utils.Constants.CHANGE_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.DELETE_BOOK;
import static com.golubovich.elibrary.utils.Constants.DELETE_CLIENT;
import static com.golubovich.elibrary.utils.Constants.DELETE_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.DELETE_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.REGISTER_CLIENT;
import static com.golubovich.elibrary.utils.Constants.SHOW_BOOKS;
import static com.golubovich.elibrary.utils.Constants.SHOW_CLIENTS;
import static com.golubovich.elibrary.utils.Constants.SHOW_ED_MATERIALS;
import static com.golubovich.elibrary.utils.Constants.SHOW_GENRES;
import static com.golubovich.elibrary.utils.Constants.SHOW_LIBRARY;
import static com.golubovich.elibrary.utils.Constants.SHOW_MAGAZINES;
import static com.golubovich.elibrary.utils.Constants.SHOW_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_BOOK;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_MAGAZINE;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.controller.impl.commands.AuthorizationCommand;
import com.golubovich.elibrary.controller.impl.commands.ChangeInfoCommand;
import com.golubovich.elibrary.controller.impl.commands.client.DeleteClientCommand;
import com.golubovich.elibrary.controller.impl.commands.client.RegisterClientCommand;
import com.golubovich.elibrary.controller.impl.commands.ShowInfoCommand;
import com.golubovich.elibrary.controller.impl.commands.genre.AddGenreCommand;
import com.golubovich.elibrary.controller.impl.commands.genre.ShowGenresCommand;
import com.golubovich.elibrary.controller.impl.commands.item.AddItemCommand;
import com.golubovich.elibrary.controller.impl.commands.item.AddItemReviewCommand;
import com.golubovich.elibrary.controller.impl.commands.item.DeleteItemCommand;
import com.golubovich.elibrary.controller.impl.commands.item.ShowItemReviewsCommand;
import com.golubovich.elibrary.controller.impl.commands.item.ShowItemsCommand;
import com.golubovich.elibrary.controller.impl.commands.library.ChangeLibraryCommand;
import com.golubovich.elibrary.controller.impl.commands.library.ShowLibraryCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

  private final Map<String, Command> commands = new HashMap();

  public CommandProvider() {
    // for both admin & client
    this.commands.put(AUTHORIZE, new AuthorizationCommand());
    this.commands.put(CHANGE_PERSONAL_DATA, new ChangeInfoCommand());
    this.commands.put(SHOW_PERSONAL_DATA, new ShowInfoCommand());

    this.commands.put(SHOW_CLIENTS, new ShowInfoCommand());
    this.commands.put(REGISTER_CLIENT, new RegisterClientCommand());
    this.commands.put(CHANGE_CLIENT_STATUS, new ChangeInfoCommand());
    this.commands.put(DELETE_CLIENT, new DeleteClientCommand());

    this.commands.put(ADD_MAGAZINE, new AddItemCommand());
    this.commands.put(SHOW_MAGAZINES, new ShowItemsCommand());
    this.commands.put(DELETE_MAGAZINE, new DeleteItemCommand());
    this.commands.put(SHOW_REVIEWS_MAGAZINE, new ShowItemReviewsCommand());
    this.commands.put(ADD_REVIEW_MAGAZINE, new AddItemReviewCommand());

    this.commands.put(ADD_BOOK, new AddItemCommand());
    this.commands.put(SHOW_BOOKS, new ShowItemsCommand());
    this.commands.put(DELETE_BOOK, new DeleteItemCommand());
    this.commands.put(SHOW_REVIEWS_BOOK, new ShowItemReviewsCommand());
    this.commands.put(ADD_REVIEW_BOOK, new AddItemReviewCommand());

    this.commands.put(ADD_ED_MATERIAL, new AddItemCommand());
    this.commands.put(SHOW_ED_MATERIALS, new ShowItemsCommand());
    this.commands.put(DELETE_ED_MATERIAL, new DeleteItemCommand());
    this.commands.put(SHOW_REVIEWS_ED_MATERIAL, new ShowItemReviewsCommand());
    this.commands.put(ADD_REVIEW_ED_MATERIAL, new AddItemReviewCommand());

    this.commands.put(ADD_GENRE, new AddGenreCommand());
    this.commands.put(SHOW_GENRES, new ShowGenresCommand());

    this.commands.put(SHOW_LIBRARY, new ShowLibraryCommand());
    this.commands.put(CHANGE_LIBRARY, new ChangeLibraryCommand());
  }

  public Command getCommand(String commandName) {
    return this.commands.get(commandName);
  }
}

