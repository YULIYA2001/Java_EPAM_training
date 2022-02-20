package com.golubovich.elibrary.controller.impl.commands.genre;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.GenreService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AddGenreCommand implements Command {
    public AddGenreCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length != 3) {
            return "1 error AddGenreCommand";
        } else {
            GenreService genreService = provider.getGenreService();
            String[] args = new String[2];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            boolean result = genreService.add(args);
            return ActionViewer.addItemAnswer(result);
        }
    }
}

