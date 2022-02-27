package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;

public class ShowItemsCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        ItemService itemService = null;

        switch(params[0]) {
            case "show-ed_materials":
                itemService = provider.getEdMaterialService();
                break;
            case "show-books":
                itemService = provider.getBookService();
                break;
            case "show-magazines":
                itemService = provider.getMagazineService();
        }

        if (itemService != null) {
            return "0 " + itemService.showAll();
        }

        return "1 error in ShowItemsCommand";
    }
}

