package com.golubovich.elibrary.controller.impl.commands.client;

import static com.golubovich.elibrary.utils.Constants.FAIL_DELETE;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_DELETE;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ClientService;
import org.apache.log4j.Logger;

public class DeleteClientCommand implements Command {

  private static final Logger log = Logger.getLogger(DeleteClientCommand.class);

  public String execute(String[] params) {

    if (params.length == 2) {
      ServiceProvider provider = ServiceProvider.getInstance();
      ClientService clientService = provider.getClientService();

      try {
        String eMail = params[1].split("=")[1];
        return clientService.deleteByEMail(eMail) ? SUCCESSFUL_DELETE : FAIL_DELETE;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return FAIL_DELETE;
  }
}
