package com.iucse.passnet.recruitment.usecase.task;

import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandActionTaskRunner implements Runnable{

    private ActionCommand command;

    @Override
    public void run() {
        this.command.execute();
    }
}
