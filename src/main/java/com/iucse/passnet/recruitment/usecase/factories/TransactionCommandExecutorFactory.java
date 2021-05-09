package com.iucse.passnet.recruitment.usecase.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionCommandExecutorFactory {
	private final CommandExecutorFactory commandExecutorFactory;

	@Autowired
	public TransactionCommandExecutorFactory(CommandExecutorFactory commandExecutorFactory) {
		this.commandExecutorFactory = commandExecutorFactory;
	}
}
