package com.golubovich.elibrary.controller.impl.commands;

import static com.golubovich.elibrary.utils.Constants.SHOW_CLIENTS;
import static com.golubovich.elibrary.utils.Constants.SHOW_PERSONAL_DATA;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.WRONG_COMMAND;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import org.apache.log4j.Logger;

public class ShowInfoCommand implements Command {

  private static final Logger log = Logger.getLogger(ShowInfoCommand.class);

  public String execute(String[] params) {
    ServiceProvider provider = ServiceProvider.getInstance();

    switch (params[0]) {
      case SHOW_CLIENTS:
        if (params.length == 1) {
          ClientService clientService = provider.getClientService();
          try {
            return "0 " + clientService.showAll();
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        } else {
          log.error(WRONG_PARAMS_COUNT);
        }
        return SMTH_WRONG;
      case SHOW_PERSONAL_DATA:
        // admin
        if (params.length == 1) {
          AdminService adminService = provider.getAdminService();
          try {
            return "0 " + adminService.show();
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        }
        // client
        else if (params.length == 2) {
          ClientService clientService = provider.getClientService();
          try {
            return "0 " + clientService.showByEMail(params[1].split("=")[1]);
          } catch (ServiceException e) {
            log.error(e.getMessage());
          }
        } else {
          log.error(WRONG_PARAMS_COUNT);
        }
        return SMTH_WRONG;
      default:
        log.error(WRONG_COMMAND);
        return WRONG_COMMAND;
    }
  }
}

