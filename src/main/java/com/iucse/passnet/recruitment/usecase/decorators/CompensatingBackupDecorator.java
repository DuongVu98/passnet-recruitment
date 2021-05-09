package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;

public class CompensatingBackupDecorator extends CommandExecutorDecorator{
    private CompensatingCommandBackupService compensatingBackupService;

    public CompensatingBackupDecorator(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public Job execute(BaseCommand command) {
        return null;
    }
}
