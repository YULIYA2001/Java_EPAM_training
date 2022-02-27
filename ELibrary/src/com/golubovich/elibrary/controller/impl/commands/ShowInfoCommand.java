package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;

public class ShowInfoCommand implements Command {

    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        // all clients
        if ("show-clients".equals(params[0])) {
            ClientService clientService = provider.getClientService();
            return "0 " + clientService.showAll();
        }

        // admin
        if (params.length == 1) {
            AdminService adminService = provider.getAdminService();
            return "0 " + adminService.show();
        }

        // client
        if (params.length == 2) {
            ClientService clientService = provider.getClientService();
            return "0 " + clientService.showByEMail(params[1].split("=")[1]);
        }

        return "1 error";
    }
}

