package com.golubovich.elibrary.controller.impl.commands.library;

import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.LibraryService;
import org.apache.log4j.Logger;

public class ShowLibraryCommand implements Command {

  private static final Logger log = Logger.getLogger(ShowLibraryCommand.class);

  public String execute(String[] params) {

    if (params.length == 1) {
      ServiceProvider provider = ServiceProvider.getInstance();
      LibraryService libraryService = provider.getLibraryService();
      try {
        return "0 " + libraryService.showInfo();
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return SMTH_WRONG;
  }
}
