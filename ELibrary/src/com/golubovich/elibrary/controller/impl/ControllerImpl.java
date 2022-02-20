package com.golubovich.elibrary.controller.impl;

import com.golubovich.elibrary.controller.CommandProvider;
import com.golubovich.elibrary.controller.api.Command;
import com.golubovich.elibrary.controller.api.Controller;

public class ControllerImpl implements Controller {
    private CommandProvider provider = new CommandProvider();

    public ControllerImpl() {
    }

    public String doAction(String request, Object object) {
        String[] params = request.split("\\s+");
        String commandName = params[0];
        Command currentCommand = this.provider.getCommand(commandName);
        String response = currentCommand.execute(params, object);
        return response;
    }
}
