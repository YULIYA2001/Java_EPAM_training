package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.exceptions.ServiceException;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class AuthorizationCommand implements Command {
    public AuthorizationCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider;
        String e_mail;
        if (params.length == 2) {
            provider = ServiceProvider.getInstance();
            AdminService adminService = provider.getAdminService();
            e_mail = params[1].split("=")[1];

            try {
                boolean result = adminService.authorize(e_mail);
                return ActionViewer.authorizationAnswer(result);
            } catch (ServiceException var8) {
                return "1 error";
            }
        } else if (params.length == 3) {
            provider = ServiceProvider.getInstance();
            ClientService clientService = provider.getClientService();
            e_mail = params[1].split("=")[1];
            String password = params[2].split("=")[1];
            boolean result = clientService.authorize(e_mail, password);
            return ActionViewer.authorizationAnswer(result);
        } else {
            return "1 error";
        }
    }
}

