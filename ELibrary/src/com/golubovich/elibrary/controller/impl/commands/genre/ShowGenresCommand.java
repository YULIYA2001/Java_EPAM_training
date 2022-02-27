package com.golubovich.elibrary.controller.impl.commands.genre;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.GenreService;

public class ShowGenresCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 1) {
            GenreService genreService = provider.getGenreService();
            return "0 " + genreService.showAll();
        }

        return "1 error ShowGenresCommand";
    }
}

