package com.golubovich.elibrary.controller.impl.commands.item;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AddItemReviewCommand implements Command {

    public String execute(String[] params) {
        if (params.length == 3) {
            ServiceProvider provider = ServiceProvider.getInstance();
            ItemService itemService = null;
            switch(params[0]) {
                case "add-review-book":
                    itemService = provider.getBookService();
                    break;
                case "add-review-ed_material":
                    itemService = provider.getEdMaterialService();
                    break;
                case "add-review-magazine":
                    itemService = provider.getMagazineService();
            }

            if (itemService != null) {
                int code = Integer.parseInt(params[1].split("=")[1]);
                String review = params[2].split("=")[1].replace('_', ' ');
                boolean result = itemService.addReviewByCode(code, review);
                return ActionViewer.addItemReview(result);
            }
        }
        return "1 error addItemReview";
    }
}

