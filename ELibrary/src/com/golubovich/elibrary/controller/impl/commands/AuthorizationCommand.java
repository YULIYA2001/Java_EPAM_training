package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AuthorizationCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider;
        String eMail;

        // admin
        if (params.length == 2) {
            provider = ServiceProvider.getInstance();
            AdminService adminService = provider.getAdminService();
            eMail = params[1].split("=")[1];

            //try {
                boolean result = adminService.authorize(eMail);
                return ActionViewer.authorizationAnswer(result);
            //} catch (ServiceException var8) {
            //    return "1 error";
            //}
        }

        // client
        if (params.length == 3) {
            provider = ServiceProvider.getInstance();
            ClientService clientService = provider.getClientService();
            eMail = params[1].split("=")[1];
            String password = params[2].split("=")[1];
            boolean result = clientService.authorize(eMail, password);
            return ActionViewer.authorizationAnswer(result);
        }

        return "1 error";
    }
}

