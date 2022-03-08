package com.golubovich.elibrary.controller.impl.commands;

import static com.golubovich.elibrary.utils.Constants.ADD_MAGAZINE;
import static com.golubovich.elibrary.utils.Constants.FAIL_ADMIN_AUTHORIZATION;
import static com.golubovich.elibrary.utils.Constants.FAIL_CLIENT_AUTHORIZATION;
import static com.golubovich.elibrary.utils.Constants.SMTH_WRONG;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_ADMIN_AUTHORIZATION;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_CLIENT_AUTHORIZATION;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import org.apache.log4j.Logger;

public class AuthorizationCommand implements Command {

  private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

  public String execute(String[] params) {

    ServiceProvider provider;
    String eMail;

    // admin
    if (params.length == 2) {
      provider = ServiceProvider.getInstance();
      AdminService adminService = provider.getAdminService();
      eMail = params[1].split("=")[1];

      try {
        return adminService.authorize(eMail) ?
            SUCCESSFUL_ADMIN_AUTHORIZATION : FAIL_ADMIN_AUTHORIZATION;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    }
    // client
    else if (params.length == 3) {
      provider = ServiceProvider.getInstance();
      ClientService clientService = provider.getClientService();
      eMail = params[1].split("=")[1];
      String password = params[2].split("=")[1];

      try {
        return clientService.authorize(eMail, password) ?
            SUCCESSFUL_CLIENT_AUTHORIZATION : FAIL_CLIENT_AUTHORIZATION;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return SMTH_WRONG;
  }
}

