package com.user.command.app.command;

import com.user.command.app.model.Result;

public interface Command {
    public Result execute (String [] attributes) throws Exception;
}
