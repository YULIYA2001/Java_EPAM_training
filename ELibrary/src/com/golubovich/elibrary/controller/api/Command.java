package com.golubovich.elibrary.controller.api;

public interface Command {
    String execute(String[] params);
}
