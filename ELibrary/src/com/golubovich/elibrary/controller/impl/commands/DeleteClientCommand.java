package com.golubovich.elibrary.controller.impl.commands;

import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.service.ServiceProvider;
import com.golubovich.elibrary.service.api.ClientService;
import com.golubovich.elibrary.view.presentation.ActionViewer;

public class DeleteClientCommand implements Command {
    public DeleteClientCommand() {
    }

    public String execute(String[] params, Object object) {
        ServiceProvider provider = ServiceProvider.getInstance();
        if (params.length == 2) {
            ClientService clientService = provider.getClientService();
            boolean result = clientService.deleteByEMail(params[1].split("=")[1]);
            return ActionViewer.deleteAnswer(result);
        } else {
            return "1 error";
        }
    }
}
