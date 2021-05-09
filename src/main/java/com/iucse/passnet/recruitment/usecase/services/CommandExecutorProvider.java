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

	public StudentApplyJobCommandExecutor produceStudentApplyJobCommandExecutor() {
		return StudentApplyJobCommandExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	public AcceptJobApplicationCommandExecutor produceAcceptJobApplicationCommandExecutor() {
		return AcceptJobApplicationCommandExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	public TeacherPostJobCommandExecutor produceTeacherPostJobCommandExecutor() {
		return TeacherPostJobCommandExecutor
			.builder()
			.jobRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	public RemoveJobApplicationCommandExecutor produceRemoveJobApplicationCommandExecutor() {
		return RemoveJobApplicationCommandExecutor.builder().jobRepository(this.aggregateRepository).build();
	}

	public TeacherDeleteJobCommandExecutor produceTeacherDeleteJobCommandExecutor() {
		return TeacherDeleteJobCommandExecutor.builder().jobRepository(aggregateRepository).build();
	}
}
