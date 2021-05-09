package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.usecase.decorators.CommandExecutorDecoratorTypes;
import com.iucse.passnet.recruitment.usecase.decorators.CompensatingBackupDecorator;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutorDecoratorFactory {
	private final CompensatingCommandBackupService compensatingBackupService;
	private final CompensatingCommandProvider compensatingCommandProvider;

	@Autowired
	public CommandExecutorDecoratorFactory(
		CompensatingCommandBackupService compensatingBackupService,
		CompensatingCommandProvider compensatingCommandProvider
	) {
		this.compensatingBackupService = compensatingBackupService;
		this.compensatingCommandProvider = compensatingCommandProvider;
	}

	public CommandExecutor produce(CommandExecutor commandExecutor, CommandExecutorDecoratorTypes decorator) {
		switch (decorator) {
			case COMPENSATING_COMMAND_BACKUP:
				return this.produceCompensatingBackupDecorator(commandExecutor);
			default:
				return commandExecutor;
		}
	}

	private CommandExecutor produceCompensatingBackupDecorator(CommandExecutor commandExecutor) {
		return CompensatingBackupDecorator
			.builder()
			.commandExecutor(commandExecutor)
			.compensatingCommandProvider(this.compensatingCommandProvider)
			.compensatingBackupService(this.compensatingBackupService)
			.build();
	}
}
