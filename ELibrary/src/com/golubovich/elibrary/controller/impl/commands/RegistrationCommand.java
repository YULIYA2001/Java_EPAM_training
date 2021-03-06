package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class RegistrationCommand implements Command {

    public String execute(String[] params) {
        if (params.length == 7) {
            ServiceProvider provider = ServiceProvider.getInstance();
            ClientService clientService = provider.getClientService();
            String[] args = new String[6];

            for(int i = 1; i < params.length; ++i) {
                args[i - 1] = params[i].split("=")[1];
            }

            boolean result = clientService.register(args);
            return ActionViewer.registrationClientAnswer(result);
        }

        return "1 error";
    }
}

