package com.golubovich.elibrary.controller.impl.commands.library;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.LibraryService;

public class ShowLibraryCommand implements Command {
    public ShowLibraryCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length == 1) {
            LibraryService libraryService = provider.getLibraryService();
            String library = libraryService.showInfo();
            return "0 " + library;
        } else {
            return "1 error ShowLibraryCommand";
        }
    }
}
