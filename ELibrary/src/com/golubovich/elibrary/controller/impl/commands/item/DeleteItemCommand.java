package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class DeleteItemCommand implements Command {
    public DeleteItemCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length == 2) {
            String var5 = params[0];
            byte var6 = -1;
            switch(var5.hashCode()) {
                case -1168528949:
                    if (var5.equals("delete-book")) {
                        var6 = 1;
                    }
                    break;
                case -1029383818:
                    if (var5.equals("delete-magazine")) {
                        var6 = 0;
                    }
                    break;
                case 1202305957:
                    if (var5.equals("delete-ed_material")) {
                        var6 = 2;
                    }
            }

            ItemService itemService;
            switch(var6) {
                case 0:
                    itemService = provider.getMagazineService();
                    break;
                case 1:
                    itemService = provider.getBookService();
                    break;
                case 2:
                    itemService = provider.getEdMaterialService();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + params[0]);
            }

            boolean result = itemService.deleteByCode(Integer.parseInt(params[1].split("=")[1]));
            return ActionViewer.deleteAnswer(result);
        } else {
            return "1 error";
        }
    }
}

