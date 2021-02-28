package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class TeacherPostJobCommandExecutor extends AbstractCommandExecutor<TeacherPostJobCommand, Job> {
	private final UUIDGeneratorService uuidGeneratorService;

	@Builder
	public TeacherPostJobCommandExecutor(
		JobAggregateRepository aggregateRepository,
		UUIDGeneratorService uuidGeneratorService
	) {
		super(aggregateRepository);
		this.uuidGeneratorService = uuidGeneratorService;
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

		return this.aggregateRepository.save(newJob);
	}
}
