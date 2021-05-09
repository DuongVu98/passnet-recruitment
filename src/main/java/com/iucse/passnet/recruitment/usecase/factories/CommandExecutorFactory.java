package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.domain.compensating.AcceptJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.exceptions.CommandExecutorNotFoundException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.executors.*;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import org.springframework.stereotype.Service;

@Service("transaction-command-executor")
public class CommandExecutorFactory {
	private final JobAggregateRepository aggregateRepository;
	private final UUIDGeneratorService uuidGeneratorService;

	public CommandExecutorFactory(JobAggregateRepository aggregateRepository, UUIDGeneratorService uuidGeneratorService) {
		this.aggregateRepository = aggregateRepository;
		this.uuidGeneratorService = uuidGeneratorService;
	}

	public CommandExecutor produce(BaseCommand command) {
		if (command instanceof StudentApplyJobCommand) {
			return this.produceStudentApplyJobCommand();
		} else if (command instanceof AcceptJobApplicationCommand) {
			return this.produceAcceptJobApplicationCommand();
		} else if (command instanceof TeacherPostJobCommand) {
			return this.produceTeacherPostJobCommand();
		} else if (command instanceof RemoveJobApplicationCommand) {
			return this.produceRemoveJobApplicationCommand();
		} else if (command instanceof TeacherDeleteJobCommand) {
			return this.produceTeacherDeleteJobCommand();
		} else {
			throw new CommandExecutorNotFoundException("command executor not found");
		}
	}

	public CommandExecutor produce(CompensatingCommand command) {
		if (command instanceof AcceptJobApplicationCompensating) {
			return this.produceAcceptJobApplicationCommand();
		} else if (command instanceof RemoveJobApplicationCompensating) {
			return this.produceRemoveJobApplicationCommand();
		} else {
			throw new CommandExecutorNotFoundException("command executor not found");
		}
	}

	private CommandExecutor produceStudentApplyJobCommand() {
		return StudentApplyJobCommandExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	private CommandExecutor produceAcceptJobApplicationCommand() {
		return AcceptJobApplicationCommandExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	private CommandExecutor produceTeacherPostJobCommand() {
		return TeacherPostJobCommandExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	private CommandExecutor produceRemoveJobApplicationCommand() {
		return RemoveJobApplicationCommandExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	private CommandExecutor produceTeacherDeleteJobCommand() {
		return TeacherDeleteJobCommandExecutor.builder().jobRepository(aggregateRepository).build();
	}
}
