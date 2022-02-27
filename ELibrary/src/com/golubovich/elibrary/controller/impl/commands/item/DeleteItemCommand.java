package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class DeleteItemCommand implements Command {

    public String execute(String[] params) {
        if (params.length == 2) {
            ServiceProvider provider = ServiceProvider.getInstance();
            ItemService itemService = null;

            switch(params[0]) {
                case "delete-book":
                    itemService = provider.getBookService();
                    break;
                case "delete-magazine":
                    itemService = provider.getMagazineService();
                    break;
                case "delete-ed_material":
                    itemService = provider.getEdMaterialService();
            }

            if (itemService != null) {
                boolean result = itemService.deleteByCode(Integer.parseInt(params[1].split("=")[1]));
                return ActionViewer.deleteAnswer(result);
            }
        }
        return "1 error";
    }
}

