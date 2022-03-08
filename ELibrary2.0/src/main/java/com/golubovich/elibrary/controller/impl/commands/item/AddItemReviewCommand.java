package com.golubovich.elibrary.controller.impl.commands.item;

import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_REVIEW_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.FAIL_ITEM_REVIEW_ADD;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_ITEM_REVIEW_ADD;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import org.apache.log4j.Logger;

public class AddItemReviewCommand implements Command {

  private static final Logger log = Logger.getLogger(AddItemReviewCommand.class);

  public String execute(String[] params) {
    if (params.length == 3) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ItemService itemService = switch (params[0]) {
        case ADD_REVIEW_BOOK -> provider.getBookService();
        case ADD_REVIEW_ED_MATERIAL -> provider.getEdMaterialService();
        case ADD_REVIEW_MAGAZINE -> provider.getMagazineService();
        default -> null;
      };

      if (itemService != null) {
        int code;
        try {
          code = Integer.parseInt(params[1].split("=")[1]);
        } catch (NumberFormatException e) {
          log.error(e.getMessage());
          return SMTH_WRONG;
        }
        String review = params[2].split("=")[1].replace('_', ' ');
        try {
          return itemService.addReviewByCode(code, review) ?
              SUCCESSFUL_ITEM_REVIEW_ADD : FAIL_ITEM_REVIEW_ADD;
        } catch (ServiceException e) {
          log.error(e.getMessage());
        }
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }
    return FAIL_ITEM_REVIEW_ADD;
  }
}

