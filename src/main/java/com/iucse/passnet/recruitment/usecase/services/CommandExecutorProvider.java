package com.iucse.passnet.recruitment.usecase.services;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.executors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorProvider {
	private final JobAggregateRepository aggregateRepository;
	private final UUIDGeneratorService uuidGeneratorService;

	@Autowired
	public CommandExecutorProvider(JobAggregateRepository aggregateRepository, UUIDGeneratorService uuidGeneratorService) {
		this.aggregateRepository = aggregateRepository;
		this.uuidGeneratorService = uuidGeneratorService;
	}

	public ApplyJobExecutor produceStudentApplyJobCommandExecutor() {
		return ApplyJobExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	public AcceptJobApplicationExecutor produceAcceptJobApplicationCommandExecutor() {
		return AcceptJobApplicationExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	public PostJobExecutor produceTeacherPostJobCommandExecutor() {
		return PostJobExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	public RemoveJobApplicationExecutor produceRemoveJobApplicationCommandExecutor() {
		return RemoveJobApplicationExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	public DeleteJobExecutor produceTeacherDeleteJobCommandExecutor() {
		return DeleteJobExecutor.builder().jobRepository(aggregateRepository).build();
	}
}
