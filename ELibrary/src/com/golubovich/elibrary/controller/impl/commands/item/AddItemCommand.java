package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AddItemCommand implements Command {
    public AddItemCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        ItemService edMaterialService;
        String[] args;
        int i;
        boolean result;
        if (params.length == 6 && params[0].equals("add-magazine")) {
            edMaterialService = provider.getMagazineService();
            args = new String[5];

            for(i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            result = edMaterialService.add(args);
            return ActionViewer.addItemAnswer(result);
        } else if (params.length == 5 && params[0].equals("add-book")) {
            edMaterialService = provider.getBookService();
            args = new String[4];

            for(i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            result = edMaterialService.add(args);
            return ActionViewer.addItemAnswer(result);
        } else if (params.length == 6 && params[0].equals("add-ed_material")) {
            edMaterialService = provider.getEdMaterialService();
            args = new String[5];

            for(i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            result = edMaterialService.add(args);
            return ActionViewer.addItemAnswer(result);
        } else {
            return "1 error AddItemCommand";
        }
    }
}

