package com.golubovich.elibrary.controller.impl.commands.genre;

import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.GenreService;
import org.apache.log4j.Logger;

public class ShowGenresCommand implements Command {

  private static final Logger log = Logger.getLogger(ShowGenresCommand.class);

  public String execute(String[] params) {
    ServiceProvider provider = ServiceProvider.getInstance();

    if (params.length == 1) {
      GenreService genreService = provider.getGenreService();
      try {
        return "0 " + genreService.showAll();
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }
    return SMTH_WRONG;
  }
}

