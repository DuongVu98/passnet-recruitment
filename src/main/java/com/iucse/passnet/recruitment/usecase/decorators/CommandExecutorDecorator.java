package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;

public abstract class CommandExecutorDecorator implements CommandExecutor {
    private CommandExecutor commandExecutor;

    public CommandExecutorDecorator(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
