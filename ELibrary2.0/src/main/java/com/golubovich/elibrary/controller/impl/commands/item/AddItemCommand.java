package com.golubovich.elibrary.controller.impl.commands.item;

import static com.golubovich.elibrary.utils.Constants.ADD_BOOK;
import static com.golubovich.elibrary.utils.Constants.ADD_ED_MATERIAL;
import static com.golubovich.elibrary.utils.Constants.ADD_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.FAIL_BOOK_ADD;
import static com.golubovich.elibrary.utils.Constants.FAIL_ED_MATERIAL_ADD;
import static com.golubovich.elibrary.utils.Constants.FAIL_MAGAZINE_ADD;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_BOOK_ADD;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_ED_MATERIAL_ADD;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_MAGAZINE_ADD;
import static com.golubovich.elibrary.utils.Constants.WRONG_COMMAND;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ItemService;
import org.apache.log4j.Logger;

public class AddItemCommand implements Command {

  private static final Logger log = Logger.getLogger(AddItemCommand.class);

  public String execute(String[] params) {
    ServiceProvider provider = ServiceProvider.getInstance();

    switch (params[0]) {
      case ADD_MAGAZINE:
        if (params.length == 6) {
          ItemService itemService = provider.getMagazineService();

          String[] args = new String[5];
          for (int i = 1; i < params.length; ++i) {
            args[i - 1] = params[i].split("=")[1];
          }

          try {
            return itemService.add(args) ? SUCCESSFUL_MAGAZINE_ADD : FAIL_MAGAZINE_ADD;
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        }
        else {
          log.error(WRONG_PARAMS_COUNT);
        }
        return FAIL_MAGAZINE_ADD;
      case ADD_BOOK:
        if (params.length == 5) {
          ItemService itemService = provider.getBookService();

          String[] args = new String[4];
          for (int i = 1; i < params.length; ++i) {
            args[i - 1] = params[i].split("=")[1];
          }

          try {
            return itemService.add(args) ? SUCCESSFUL_BOOK_ADD : FAIL_BOOK_ADD;
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        }
        else {
          log.error(WRONG_PARAMS_COUNT);
        }
        return FAIL_BOOK_ADD;
      case ADD_ED_MATERIAL:
        if (params.length == 6) {
          ItemService itemService = provider.getEdMaterialService();

          String[] args = new String[5];
          for (int i = 1; i < params.length; ++i) {
            args[i - 1] = params[i].split("=")[1];
          }

          try {
            return itemService.add(args) ? SUCCESSFUL_ED_MATERIAL_ADD : FAIL_ED_MATERIAL_ADD;
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        }
        else {
          log.error(WRONG_PARAMS_COUNT);
        }
        return FAIL_ED_MATERIAL_ADD;
      default:
        log.error(WRONG_COMMAND);
        return SMTH_WRONG;
    }
  }
}

