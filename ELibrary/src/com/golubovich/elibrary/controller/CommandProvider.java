package com.golubovich.elibrary.controller;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.controller.impl.commands.AuthorizationCommand;
import com.golubovich.elibrary.controller.impl.commands.ChangeInfoCommand;
import com.golubovich.elibrary.controller.impl.commands.DeleteClientCommand;
import com.golubovich.elibrary.controller.impl.commands.RegistrationCommand;
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
    private Map<String, Command> commands = new HashMap();

    public CommandProvider() {
        this.commands.put("authorize", new AuthorizationCommand());
        this.commands.put("change-pd", new ChangeInfoCommand());
        this.commands.put("show-pd", new ShowInfoCommand());
        this.commands.put("show-clients", new ShowInfoCommand());
        this.commands.put("register-client", new RegistrationCommand());
        this.commands.put("change-client-status", new ChangeInfoCommand());
        this.commands.put("delete-client", new DeleteClientCommand());
        this.commands.put("add-magazine", new AddItemCommand());
        this.commands.put("show-magazines", new ShowItemsCommand());
        this.commands.put("delete-magazine", new DeleteItemCommand());
        this.commands.put("show-reviews-magazine", new ShowItemReviewsCommand());
        this.commands.put("add-review-magazine", new AddItemReviewCommand());
        this.commands.put("add-book", new AddItemCommand());
        this.commands.put("show-books", new ShowItemsCommand());
        this.commands.put("delete-book", new DeleteItemCommand());
        this.commands.put("show-reviews-book", new ShowItemReviewsCommand());
        this.commands.put("add-review-book", new AddItemReviewCommand());
        this.commands.put("add-ed_material", new AddItemCommand());
        this.commands.put("show-ed_materials", new ShowItemsCommand());
        this.commands.put("delete-ed_material", new DeleteItemCommand());
        this.commands.put("show-reviews-ed_material", new ShowItemReviewsCommand());
        this.commands.put("add-review-ed_material", new AddItemReviewCommand());
        this.commands.put("add-genre", new AddGenreCommand());
        this.commands.put("show-genres", new ShowGenresCommand());
        this.commands.put("show-library", new ShowLibraryCommand());
        this.commands.put("change-library", new ChangeLibraryCommand());
    }

    public Command getCommand(String commandName) {
        Command command = (Command)this.commands.get(commandName);
        return command;
    }
}

