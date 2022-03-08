package com.golubovich.elibrary.controller.impl.commands.genre;

import static com.golubovich.elibrary.utils.Constants.FAIL_GENRE_ADD;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_GENRE_ADD;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.GenreService;
import org.apache.log4j.Logger;

public class AddGenreCommand implements Command {

  private static final Logger log = Logger.getLogger(AddGenreCommand.class);

  public String execute(String[] params) {
    ServiceProvider provider = ServiceProvider.getInstance();

    if (params.length == 3) {
      GenreService genreService = provider.getGenreService();
      String[] args = new String[2];

      for (int i = 1; i < params.length; ++i) {
        args[i - 1] = params[i].split("=")[1];
      }

      try {
        boolean result = genreService.add(args[0], args[1]);
        return result ? SUCCESSFUL_GENRE_ADD : FAIL_GENRE_ADD;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }
    return FAIL_GENRE_ADD;
  }
}

