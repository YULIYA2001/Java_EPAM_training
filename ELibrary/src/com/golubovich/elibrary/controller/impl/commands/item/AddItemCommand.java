package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AddItemCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 6 && params[0].equals("add-magazine")) {
            ItemService itemService = provider.getMagazineService();
            String[] args = new String[5];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            boolean result = itemService.add(args);
            return ActionViewer.addItemAnswer(result);
        }

        if (params.length == 5 && params[0].equals("add-book")) {
            ItemService itemService = provider.getBookService();
            String[] args = new String[4];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            boolean result = itemService.add(args);
            return ActionViewer.addItemAnswer(result);
        }

        if (params.length == 6 && params[0].equals("add-ed_material")) {
            ItemService itemService = provider.getEdMaterialService();
            String[] args = new String[5];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            boolean result = itemService.add(args);
            return ActionViewer.addItemAnswer(result);
        }

        return "1 error AddItemCommand";
    }
}

