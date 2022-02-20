package com.golubovich.elibrary.controller.impl.commands.genre;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.GenreService;

public class ShowGenresCommand implements Command {
    public ShowGenresCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length == 1) {
            GenreService genreService = provider.getGenreService();
            String genres = genreService.showAll();
            return "0 " + genres;
        } else {
            return "1 error ShowGenresCommand";
        }
    }
}

