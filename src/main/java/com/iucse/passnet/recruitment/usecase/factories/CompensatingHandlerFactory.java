package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.domain.compensating.AcceptJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.usecase.executors.CompensatingHandler;
import com.iucse.passnet.recruitment.usecase.services.CommandExecutorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompensatingHandlerFactory {
	private final CommandExecutorProvider commandExecutorProvider;

	@Autowired
	public CompensatingHandlerFactory(CommandExecutorProvider commandExecutorProvider) {
		this.commandExecutorProvider = commandExecutorProvider;
	}

	public CompensatingHandler produce(AcceptJobApplicationCompensating compensating) {
		return this.commandExecutorProvider.produceAcceptJobApplicationCommandExecutor();
	}

	public CompensatingHandler produce(RemoveJobApplicationCompensating compensating) {
		return this.commandExecutorProvider.produceRemoveJobApplicationCommandExecutor();
	}
}
