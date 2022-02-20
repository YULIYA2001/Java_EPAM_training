package com.golubovich.elibrary.controller.impl.commands.library;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.LibraryService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class ChangeLibraryCommand implements Command {
    public ChangeLibraryCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length != 4) {
            return "1 error ChangeLibraryCommand";
        } else {
            String[] args = new String[3];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            LibraryService libraryService = provider.getLibraryService();
            boolean result = libraryService.changeInfo(args);
            return ActionViewer.changeDataAnswer(result);
        }
    }
}

