package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor
public abstract class CommandExecutorDecorator implements CommandExecutor {
    protected final CommandExecutor commandExecutor;
}
