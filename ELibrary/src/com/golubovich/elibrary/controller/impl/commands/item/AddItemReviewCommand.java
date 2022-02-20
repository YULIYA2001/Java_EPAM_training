package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AddItemReviewCommand implements Command {
    public AddItemReviewCommand() {
    }

    public String execute(String[] params, Object object) {
        if (params.length == 3) {
            ServiceProvider provider = ServiceProvider.getInstance();
            String var5 = params[0];
            byte var6 = -1;
            switch(var5.hashCode()) {
                case 443866386:
                    if (var5.equals("add-review-book")) {
                        var6 = 1;
                    }
                    break;
                case 1318230654:
                    if (var5.equals("add-review-ed_material")) {
                        var6 = 2;
                    }
                    break;
                case 1876365629:
                    if (var5.equals("add-review-magazine")) {
                        var6 = 0;
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

            int code = Integer.parseInt(params[1].split("=")[1]);
            String review = params[2].split("=")[1].replace('_', ' ');
            boolean result = itemService.addReviewByCode(code, review);
            return ActionViewer.addItemReview(result);
        } else {
            return "1 error addItemReview";
        }
    }
}

