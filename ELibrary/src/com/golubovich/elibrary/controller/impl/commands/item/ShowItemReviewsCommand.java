package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;

public class ShowItemReviewsCommand implements Command {
    public ShowItemReviewsCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length == 2) {
            String reviews = params[0];
            byte var6 = -1;
            switch(reviews.hashCode()) {
                case -1344762325:
                    if (reviews.equals("show-reviews-book")) {
                        var6 = 1;
                    }
                    break;
                case 1221798725:
                    if (reviews.equals("show-reviews-ed_material")) {
                        var6 = 2;
                    }
                    break;
                case 1532661206:
                    if (reviews.equals("show-reviews-magazine")) {
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

            reviews = itemService.showReviewsByCode(Integer.parseInt(params[1].split("=")[1]));
            return "0 " + reviews;
        } else {
            return "1 error ShowItemReviewsCommand";
        }
    }
}
