package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.domain.exceptions.CommandExecutorNotFoundException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.executors.*;
import com.iucse.passnet.recruitment.usecase.grpc.RecruitmentSagaGateway;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorFactory {
	private final JobAggregateRepository aggregateRepository;
	private final UUIDGeneratorService uuidGeneratorService;
	private final RecruitmentSagaGateway recruitmentSagaGateway;

	@Autowired
	public CommandExecutorFactory(
		JobAggregateRepository aggregateRepository,
		UUIDGeneratorService uuidGeneratorService,
		RecruitmentSagaGateway recruitmentSagaGateway
	) {
		this.aggregateRepository = aggregateRepository;
		this.uuidGeneratorService = uuidGeneratorService;
		this.recruitmentSagaGateway = recruitmentSagaGateway;
	}

	public AbstractCommandExecutor produceCommandExecutor(BaseCommand command) throws CommandExecutorNotFoundException {
		if (command instanceof StudentApplyJobCommand) {
			return this.produceStudentApplyJobCommandExecutor((StudentApplyJobCommand) command);
		} else if (command instanceof TeacherAcceptStudentJobApplicationCommand) {
			return this.produceTeacherAcceptStudentJobApplicationCommandExecutor(
					(TeacherAcceptStudentJobApplicationCommand) command
				);
		} else if (command instanceof TeacherPostJobCommand) {
			return this.produceTeacherPostJobCommandExecutor((TeacherPostJobCommand) command);
		} else if (command instanceof TeacherRemoveStudentJobApplicationCommand) {
			return this.produceTeacherRemoveStudentJobApplicationCommandExecutor(
					(TeacherRemoveStudentJobApplicationCommand) command
				);
		} else if (command instanceof TeacherDeleteJobCommand) {
			return this.produceTeacherDeleteJobCommandExecutor((TeacherDeleteJobCommand) command);
		} else {
			throw new CommandExecutorNotFoundException("command executor not found");
		}
	}

	private AbstractCommandExecutor<StudentApplyJobCommand, Job> produceStudentApplyJobCommandExecutor(
		StudentApplyJobCommand command
	) {
		return StudentApplyJobCommandExecutor
			.builder()
			.aggregateRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.build();
	}

	private AbstractCommandExecutor<TeacherAcceptStudentJobApplicationCommand, Job> produceTeacherAcceptStudentJobApplicationCommandExecutor(
		TeacherAcceptStudentJobApplicationCommand command
	) {
		return TeacherAcceptStudentJobApplicationCommandExecutor
			.builder()
			.recruitmentSagaGateway(this.recruitmentSagaGateway)
			.aggregateRepository(this.aggregateRepository)
			.build();
	}

	private AbstractCommandExecutor<TeacherPostJobCommand, Job> produceTeacherPostJobCommandExecutor(
		TeacherPostJobCommand command
	) {
		return TeacherPostJobCommandExecutor
			.builder()
			.aggregateRepository(this.aggregateRepository)
			.uuidGeneratorService(this.uuidGeneratorService)
			.recruitmentSagaGateway(this.recruitmentSagaGateway)
			.build();
	}

	private AbstractCommandExecutor<TeacherRemoveStudentJobApplicationCommand, Job> produceTeacherRemoveStudentJobApplicationCommandExecutor(
		TeacherRemoveStudentJobApplicationCommand command
	) {
		return TeacherRemoveStudentJobApplicationCommandExecutor
			.builder()
			.aggregateRepository(this.aggregateRepository)
			.recruitmentSagaGateway(this.recruitmentSagaGateway)
			.build();
	}

	private AbstractCommandExecutor<TeacherDeleteJobCommand, Job> produceTeacherDeleteJobCommandExecutor(
		TeacherDeleteJobCommand command
	) {
		return TeacherDeleteJobCommandExecutor
			.builder()
			.aggregateRepository(aggregateRepository)
			.recruitmentSagaGateway(recruitmentSagaGateway)
			.build();
	}
}
