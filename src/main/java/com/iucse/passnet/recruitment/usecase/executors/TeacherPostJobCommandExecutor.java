package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.vos.*;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class TeacherPostJobCommandExecutor implements CommandExecutor {
	private final JobAggregateRepository jobRepository;
	private final UUIDGeneratorService uuidGeneratorService;

	@Builder
	public TeacherPostJobCommandExecutor(JobAggregateRepository jobRepository, UUIDGeneratorService uuidGeneratorService) {
		this.jobRepository = jobRepository;
		this.uuidGeneratorService = uuidGeneratorService;
	}

	@Override
	public Job execute(BaseCommand baseCommand) {
		if (baseCommand instanceof TeacherPostJobCommand) {
			TeacherPostJobCommand command = (TeacherPostJobCommand) baseCommand;

			Job newJob = Job
				.builder()
				.id(new JobId(uuidGeneratorService.generate().toString()))
				.jobName(new JobName(command.getJobName()))
				.courseName(new CourseName(command.getCourseName()))
				.content(new Content(command.getContent()))
				.jobRequirement(new JobRequirement(command.getRequirement()))
				.semester(new Semester(command.getSemester()))
				.jobOwner(new UserId(command.getJobOwnerId()))
				.build();

			return this.jobRepository.save(newJob);
		} else {
			throw new WrongCommandTypeException("command must be TeacherPostJobCommand");
		}
	}
}
