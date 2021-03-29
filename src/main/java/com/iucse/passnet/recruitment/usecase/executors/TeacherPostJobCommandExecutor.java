package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.domain.events.produce.PostNewJobEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.grpc.RecruitmentSagaGateway;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;
import org.greenrobot.eventbus.EventBus;

public class TeacherPostJobCommandExecutor extends AbstractCommandExecutor<TeacherPostJobCommand, Job> {
	private final UUIDGeneratorService uuidGeneratorService;
	private final RecruitmentSagaGateway recruitmentSagaGateway;

	@Builder
	public TeacherPostJobCommandExecutor(
		JobAggregateRepository aggregateRepository,
		UUIDGeneratorService uuidGeneratorService,
		RecruitmentSagaGateway recruitmentSagaGateway
	) {
		super(aggregateRepository);
		this.uuidGeneratorService = uuidGeneratorService;
		this.recruitmentSagaGateway = recruitmentSagaGateway;
	}

	@Override
	public Job execute(TeacherPostJobCommand teacherPostJobCommand) {
		Job newJob = Job
			.builder()
			.id(new JobId(uuidGeneratorService.generate().toString()))
			.jobName(new JobName(teacherPostJobCommand.getJobName()))
			.courseName(new CourseName(teacherPostJobCommand.getCourseName()))
			.content(new Content(teacherPostJobCommand.getContent()))
			.jobRequirement(new JobRequirement(teacherPostJobCommand.getRequirement()))
			.semester(new Semester(teacherPostJobCommand.getSemester()))
			.jobOwner(new UserId(teacherPostJobCommand.getJobOwnerId()))
			.build();

		Job savedJob = this.aggregateRepository.save(newJob);

//		recruitmentSagaGateway.producePostNewJobEvent(
//			PostNewJobEvent.builder()
//				.jobId(savedJob.getId().getValue())
//				.ownerId(savedJob.getJobOwner().getValue())
//				.build()
//		);

		EventBus.getDefault().post(
			PostNewJobEvent.builder()
				.jobId(savedJob.getId().getValue())
				.ownerId(savedJob.getJobOwner().getValue())
				.build());

		return savedJob;
	}
}
