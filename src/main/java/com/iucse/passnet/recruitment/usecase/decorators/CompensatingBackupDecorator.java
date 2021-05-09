package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandProvider;
import lombok.Builder;

public class CompensatingBackupDecorator extends CommandExecutorDecorator {
	private final CompensatingCommandBackupService compensatingBackupService;
	private final CompensatingCommandProvider compensatingCommandProvider;

	@Builder
	public CompensatingBackupDecorator(
		CommandExecutor commandExecutor,
		CompensatingCommandBackupService compensatingBackupService,
		CompensatingCommandProvider compensatingCommandProvider
	) {
		super(commandExecutor);
		this.compensatingBackupService = compensatingBackupService;
		this.compensatingCommandProvider = compensatingCommandProvider;
	}

	@Override
	public Job execute(BaseCommand command) {
		this.backupCompensatingCommand(command);
		return this.commandExecutor.execute(command);
	}

	private void backupCompensatingCommand(BaseCommand command) {
		CompensatingCommand compensatingCommand = this.compensatingCommandProvider.build(command);
		this.compensatingBackupService.addToStore(compensatingCommand.getEventId(), compensatingCommand);
	}
}
