package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;

public class ShowItemReviewsCommand implements Command {

    public String execute(String[] params) {
        if (params.length == 2) {
            ServiceProvider provider = ServiceProvider.getInstance();
            ItemService itemService = null;

            switch (params[0]) {
                case "how-reviews-ed_material":
                    itemService = provider.getEdMaterialService();
                    break;
                case "show-reviews-book":
                    itemService = provider.getBookService();
                    break;
                case "show-reviews-magazine":
                    itemService = provider.getMagazineService();
            }

            if (itemService != null) {
                return "0 " + itemService.showReviewsByCode(Integer.parseInt(params[1].split("=")[1]));
            }
        }
        return "1 error ShowItemReviewsCommand";
    }
}
