package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandProvider;
import lombok.Builder;

import javax.servlet.http.HttpServletRequest;

public class CompensatingBackupDecorator extends CommandExecutorDecorator {
    private final CompensatingCommandBackupService compensatingBackupService;
    private final CompensatingCommandProvider compensatingCommandProvider;
    private final HttpServletRequest request;

    @Builder
    public CompensatingBackupDecorator(
       CommandExecutor commandExecutor,
       CompensatingCommandBackupService compensatingBackupService,
       CompensatingCommandProvider compensatingCommandProvider,
       HttpServletRequest request
    ) {
        super(commandExecutor);
        this.compensatingBackupService = compensatingBackupService;
        this.compensatingCommandProvider = compensatingCommandProvider;
        this.request = request;
    }

    @Override
    public Job execute(BaseCommand command) {
        this.backupCompensatingCommand(command);
        return this.commandExecutor.execute(command);
    }

    private void backupCompensatingCommand(BaseCommand command) {
        CompensatingCommand compensatingCommand = this.compensatingCommandProvider.build(command);
        this.compensatingBackupService.addToStore(compensatingCommand.getEventId(), compensatingCommand);
        this.request.getSession().setAttribute("eventId", compensatingCommand.getEventId());
    }
}
