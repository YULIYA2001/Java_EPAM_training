package com.golubovich.elibrary.controller.impl.commands.item;

import static com.golubovich.elibrary.utils.Constants.SHOW_BOOKS;
import static com.golubovich.elibrary.utils.Constants.SHOW_ED_MATERIALS;
import static com.golubovich.elibrary.utils.Constants.SHOW_MAGAZINES;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import org.apache.log4j.Logger;

public class ShowItemsCommand implements Command {

  private static final Logger log = Logger.getLogger(ShowItemsCommand.class);

  public String execute(String[] params) {
    if (params.length == 1) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ItemService itemService = switch (params[0]) {
        case SHOW_ED_MATERIALS -> provider.getEdMaterialService();
        case SHOW_BOOKS -> provider.getBookService();
        case SHOW_MAGAZINES -> provider.getMagazineService();
        default -> null;
      };

      if (itemService != null) {
        try {
          return "0 " + itemService.showAll();
        } catch (ServiceException e) {
          log.error(e.getMessage());
        }
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return SMTH_WRONG;
  }
}

