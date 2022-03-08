package com.golubovich.elibrary.controller.impl.commands.client;

import static com.golubovich.elibrary.utils.Constants.FAIL_CLIENT_REGISTRATION;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_CLIENT_REGISTRATION;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ClientService;
import org.apache.log4j.Logger;

public class RegisterClientCommand implements Command {

  private static final Logger log = Logger.getLogger(RegisterClientCommand.class);

  public String execute(String[] params) {
    if (params.length == 7) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ClientService clientService = provider.getClientService();

      String[] args = new String[6];
      for (int i = 1; i < params.length; ++i) {
        args[i - 1] = params[i].split("=")[1];
      }

      try {
        return clientService.register(args) ?
            SUCCESSFUL_CLIENT_REGISTRATION : FAIL_CLIENT_REGISTRATION;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return SMTH_WRONG;
  }
}

