package com.golubovich.elibrary.controller.impl.commands.item;

import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_BOOK;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.SHOW_REVIEWS_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import org.apache.log4j.Logger;

public class ShowItemReviewsCommand implements Command {

  private static final Logger log = Logger.getLogger(ShowItemReviewsCommand.class);

  public String execute(String[] params) {
    if (params.length == 2) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ItemService itemService = switch (params[0]) {
        case SHOW_REVIEWS_ED_MATERIAL -> provider.getEdMaterialService();
        case SHOW_REVIEWS_BOOK -> provider.getBookService();
        case SHOW_REVIEWS_MAGAZINE -> provider.getMagazineService();
        default -> null;
      };

      if (itemService != null) {
        try {
          int code = Integer.parseInt(params[1].split("=")[1]);
          return "0 " + itemService.showReviewsByCode(code);
        } catch (ServiceException | NumberFormatException e) {
          log.error(e.getMessage());
        }
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }
    return SMTH_WRONG;
  }
}
