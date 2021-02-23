package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.ApplicationStates;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.TeacherCreateClassroomCommand;
import com.iucse.passnet.recruitment.domain.events.produce.CreateClassEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.grpc.RecruitmentSagaGateway;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

public class TeacherCreateClassroomCommandExecutor extends AbstractCommandExecutor<TeacherCreateClassroomCommand, Job> {
	private final RecruitmentSagaGateway recruitmentSagaGateway;

	@Builder
	public TeacherCreateClassroomCommandExecutor(JobAggregateRepository aggregateRepository, RecruitmentSagaGateway recruitmentSagaGateway) {
		super(aggregateRepository);
		this.recruitmentSagaGateway = recruitmentSagaGateway;
	}

	@Override
	public Job execute(TeacherCreateClassroomCommand command) throws Throwable {
		Job job = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));
		List<String> taIds = job
			.getJobApplications()
			.stream()
			.filter(jobApplication -> jobApplication.getApplicationState().getValue().equals(ApplicationStates.ACCEPTED))
			.map(jobApplication -> jobApplication.getId().getValue())
			.collect(Collectors.toList());

		recruitmentSagaGateway.produceCreateClassEvent(CreateClassEvent.builder().teacherId(job.getJobOwner().getValue()).taIdList(taIds).build());

		this.aggregateRepository.delete(job);

		return job;
	}
}
