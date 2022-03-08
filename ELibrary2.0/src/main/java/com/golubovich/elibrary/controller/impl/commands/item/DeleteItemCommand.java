package com.golubovich.elibrary.controller.impl.commands.item;

import static com.golubovich.elibrary.utils.Constants.DELETE_BOOK;
import static com.golubovich.elibrary.utils.Constants.DELETE_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.DELETE_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.FAIL_DELETE;
import static com.golubovich.elibrary.utils.Constants.FAIL_ITEM_DELETE;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_DELETE;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import org.apache.log4j.Logger;

public class DeleteItemCommand implements Command {

  private static final Logger log = Logger.getLogger(DeleteItemCommand.class);

  public String execute(String[] params) {
    if (params.length == 2) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ItemService itemService = switch (params[0]) {
        case DELETE_BOOK -> provider.getBookService();
        case DELETE_MAGAZINE -> provider.getMagazineService();
        case DELETE_ED_MATERIAL -> provider.getEdMaterialService();
        default -> null;
      };

      if (itemService != null) {
        try {
          int code = Integer.parseInt(params[1].split("=")[1]);
          return itemService.deleteByCode(code) ? SUCCESSFUL_DELETE : FAIL_ITEM_DELETE;
        } catch (ServiceException | NumberFormatException e) {
          log.error(e.getMessage());
        }
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }
    return FAIL_ITEM_DELETE;
  }
}

