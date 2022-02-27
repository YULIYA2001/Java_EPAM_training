package com.golubovich.elibrary.controller.impl.commands.library;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.LibraryService;

public class ShowLibraryCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 1) {
            LibraryService libraryService = provider.getLibraryService();
            return "0 " + libraryService.showInfo();
        }

        return "1 error ShowLibraryCommand";
    }
}
