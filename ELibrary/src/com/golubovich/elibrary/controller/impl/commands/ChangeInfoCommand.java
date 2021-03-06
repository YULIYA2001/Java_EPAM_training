package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.enums.ClientStatus;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class ChangeInfoCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        String[] args;
        boolean result;

        // admin
        if (params.length == 5) {
            args = new String[4];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            AdminService adminService = provider.getAdminService();
            result = adminService.changeData(args[0], args[1], args[2], args[3]);
            return ActionViewer.changeDataAnswer(result);
        }

        // client
        if (params.length == 7) {
            args = new String[6];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            ClientService clientService = provider.getClientService();
            result = clientService.changeData(args[0], args[1], args[2], args[3], args[4], ClientStatus.takeByName(args[5]));
            return ActionViewer.changeDataAnswer(result);
        }

        return "1 error ChangeInfoCommand";
    }
}
