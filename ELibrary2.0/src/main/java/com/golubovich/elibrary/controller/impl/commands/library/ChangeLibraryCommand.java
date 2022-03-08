package com.golubovich.elibrary.controller.impl.commands.library;

import static com.golubovich.elibrary.utils.Constants.FAIL_CHANGE;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_CHANGE;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.LibraryService;
import org.apache.log4j.Logger;

public class ChangeLibraryCommand implements Command {

  private static final Logger log = Logger.getLogger(ChangeLibraryCommand.class);

  public String execute(String[] params) {

    if (params.length == 4) {
      String[] args = new String[3];
      for (int i = 1; i < params.length; ++i) {
        args[i - 1] = params[i].split("=")[1];
      }

      ServiceProvider provider = ServiceProvider.getInstance();
      LibraryService libraryService = provider.getLibraryService();
      try {
        return libraryService.changeInfo(args) ? SUCCESSFUL_CHANGE : FAIL_CHANGE;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return FAIL_CHANGE;
  }
}

