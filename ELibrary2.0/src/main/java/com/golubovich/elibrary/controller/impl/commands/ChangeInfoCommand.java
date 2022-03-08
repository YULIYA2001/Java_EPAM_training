package com.golubovich.elibrary.controller.impl.commands;

import static com.golubovich.elibrary.utils.Constants.FAIL_CHANGE;
import static com.golubovich.elibrary.utils.Constants.SUCCESSFUL_CHANGE;
import static com.golubovich.elibrary.utils.Constants.WRONG_PARAMS_COUNT;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import org.apache.log4j.Logger;

public class ChangeInfoCommand implements Command {

  private static final Logger log = Logger.getLogger(ChangeInfoCommand.class);

  public String execute(String[] params) {
    ServiceProvider provider = ServiceProvider.getInstance();

    // admin
    if (params.length == 5) {
      String[] args = new String[4];
      for (int i = 1; i < params.length; ++i) {
        args[i - 1] = params[i].split("=")[1];
      }

      AdminService adminService = provider.getAdminService();
      try {
        boolean result = adminService.changeData(args[0], args[1], args[2], args[3]);
        return result ? SUCCESSFUL_CHANGE : FAIL_CHANGE;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    }
    // client
    else if (params.length == 7) {
      String[] args = new String[6];
      for (int i = 1; i < params.length; ++i) {
        args[i - 1] = params[i].split("=")[1];
      }

      ClientService clientService = provider.getClientService();
      try {
        boolean result = clientService.changeData(args[0], args[1], args[2], args[3], args[4],
            ClientStatus.takeByName(args[5]));
        return result ? SUCCESSFUL_CHANGE : FAIL_CHANGE;
      } catch (ServiceException e) {
        log.error(e.getMessage());
      }
    } else {
      log.error(WRONG_PARAMS_COUNT);
    }

    return FAIL_CHANGE;
  }
}
