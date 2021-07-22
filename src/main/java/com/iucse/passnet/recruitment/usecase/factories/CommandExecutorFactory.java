package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.domain.annotation.Decorator;
import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.usecase.decorators.CommandExecutorDecoratorTypes;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CommandExecutorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transaction-command-executor")
public class CommandExecutorFactory {
    private final CommandExecutorProvider commandExecutorProvider;

    @Autowired
    public CommandExecutorFactory(CommandExecutorProvider commandExecutorProvider) {
        this.commandExecutorProvider = commandExecutorProvider;
    }

    public CommandExecutor produce(ApplyJobCommand command) {
        return this.commandExecutorProvider.produceApplyJobExecutor();
    }

    public CommandExecutor produce(AcceptJobApplicationCommand command) {
        return this.commandExecutorProvider.produceAcceptJobApplicationExecutor();
    }

    public CommandExecutor produce(PostJobCommand command) {
        return this.commandExecutorProvider.producePostJobExecutor();
    }

    @Decorator(decoratorType = {CommandExecutorDecoratorTypes.COMPENSATING_COMMAND_BACKUP, CommandExecutorDecoratorTypes.EVENT_PUBLISHER})
    public CommandExecutor produce(RemoveJobApplicationCommand command) {
        return this.commandExecutorProvider.produceJobApplicationExecutor();
    }

    public CommandExecutor produce(DeleteJobCommand command) {
        return this.commandExecutorProvider.produceDeleteJobExecutor();
    }
}
