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

	public CommandExecutor produce(StudentApplyJobCommand command) {
		return this.commandExecutorProvider.produceStudentApplyJobCommandExecutor();
	}

	@Decorator(decoratorType = CommandExecutorDecoratorTypes.COMPENSATING_COMMAND_BACKUP)
	public CommandExecutor produce(AcceptJobApplicationCommand command) {
		return this.commandExecutorProvider.produceAcceptJobApplicationCommandExecutor();
	}

	public CommandExecutor produce(TeacherPostJobCommand command) {
		return this.commandExecutorProvider.produceTeacherPostJobCommandExecutor();
	}

	@Decorator(decoratorType = CommandExecutorDecoratorTypes.COMPENSATING_COMMAND_BACKUP)
	public CommandExecutor produce(RemoveJobApplicationCommand command) {
		return this.commandExecutorProvider.produceRemoveJobApplicationCommandExecutor();
	}

	public CommandExecutor produce(TeacherDeleteJobCommand command) {
		return this.commandExecutorProvider.produceTeacherDeleteJobCommandExecutor();
	}
}
