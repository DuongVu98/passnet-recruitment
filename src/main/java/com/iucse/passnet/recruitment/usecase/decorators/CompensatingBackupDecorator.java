package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandProvider;
import lombok.experimental.SuperBuilder;

import javax.servlet.http.HttpServletRequest;

@SuperBuilder
public class CompensatingBackupDecorator extends CommandExecutorDecorator {
    private final CompensatingCommandBackupService compensatingBackupService;
    private final CompensatingCommandProvider compensatingCommandProvider;
    private final HttpServletRequest request;

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
