package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.AdminService;
import com.golubovich.elibrary.service.api.ClientService;

public class ShowInfoCommand implements Command {
    public ShowInfoCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService clientService;
        String client;
        if ("show-clients".equals(params[0])) {
            clientService = provider.getClientService();
            client = clientService.showAll();
            return "0 " + client;
        } else if (params.length == 1) {
            AdminService adminService = provider.getAdminService();
            client = adminService.show();
            return "0 " + client;
        } else if (params.length == 2) {
            clientService = provider.getClientService();
            client = clientService.findByEMail(params[1].split("=")[1]);
            return "0 " + client;
        } else {
            return "1 error";
        }
    }
}

