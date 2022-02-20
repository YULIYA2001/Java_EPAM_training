package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;

public class ShowItemsCommand implements Command {
    public ShowItemsCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        ItemService itemService = null;
        String items = params[0];
        byte var6 = -1;
        switch(items.hashCode()) {
            case -1021634212:
                if (items.equals("show-ed_materials")) {
                    var6 = 2;
                }
                break;
            case -321511462:
                if (items.equals("show-books")) {
                    var6 = 1;
                }
                break;
            case 1268821135:
                if (items.equals("show-magazines")) {
                    var6 = 0;
                }
        }

        switch(var6) {
            case 0:
                itemService = provider.getMagazineService();
                break;
            case 1:
                itemService = provider.getBookService();
                break;
            case 2:
                itemService = provider.getEdMaterialService();
        }

        if (itemService != null) {
            items = itemService.showAll();
            return "0 " + items;
        } else {
            return "1 error in ShowItemsCommand";
        }
    }
}

