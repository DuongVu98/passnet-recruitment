package com.iucse.passnet.recruitment.adapter.gateway;

import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorDecoratorFactory;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandGateway {

    private final CommandExecutorFactory commandExecutorFactory;
    private final CommandExecutorDecoratorFactory commandExecutorDecoratorFactory;

    @Async
    public void sendCommand(PostJobCommand command) {
        CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
        commandExecutor.execute(command);
    }

    @Async
    public void sendCommand(ApplyJobCommand command){
        CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
        commandExecutor.execute(command);
    }

    @Async
    public void sendCommand(AcceptJobApplicationCommand command){
        CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
        commandExecutor.execute(command);
    }

    @Async
    public void sendCommand(RemoveJobApplicationCommand command){
        CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
        commandExecutor.execute(command);
    }

    @Async
    public void sendCommand(DeleteJobCommand command){
        CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
        commandExecutor.execute(command);
    }
}
