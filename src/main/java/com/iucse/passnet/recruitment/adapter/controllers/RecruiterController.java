package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherCreateClassroomCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherDeleteJobCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.domain.forms.JobCreationForm;
import com.iucse.passnet.recruitment.domain.commands.TeacherRemoveStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.exceptions.CommandExecutorNotFoundException;
import com.iucse.passnet.recruitment.usecase.executors.AbstractCommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class RecruiterController {
	private final CommandExecutorFactory commandExecutorFactory;

	@Autowired
	public RecruiterController(CommandExecutorFactory commandExecutorFactory) {
		this.commandExecutorFactory = commandExecutorFactory;
	}

	public void postJob(JobCreationForm form, String teacherId) throws Throwable {
		TeacherPostJobCommand command = TeacherPostJobCommand
			.builder()
			.jobOwnerId(teacherId)
			.content(form.getContent())
			.jobName(form.getJobTitle())
			.courseName(form.getCourseName())
			.requirement(form.getRequirement())
			.semester(form.getSemester())
			.build();

		AbstractCommandExecutor<TeacherPostJobCommand, Job> commandExecutor = commandExecutorFactory.produceCommandExecutor(
			command
		);
		Job aggregate = commandExecutor.execute(command);
	}

	@CacheEvict(value = "job-view", key = "#jobId")
	public void acceptJobApplication(String jobApplicationId, String jobId) throws Throwable {
		TeacherAcceptStudentJobApplicationCommand command = TeacherAcceptStudentJobApplicationCommand
			.builder()
			.jobApplicationId(jobApplicationId)
			.jobId(jobId)
			.build();

		AbstractCommandExecutor<TeacherAcceptStudentJobApplicationCommand, Job> commandExecutor = commandExecutorFactory.produceCommandExecutor(
			command
		);
		Job aggregate = commandExecutor.execute(command);
	}

	public void removeJobApplication(String jobApplicationId, String jobId) throws Throwable {
		TeacherRemoveStudentJobApplicationCommand command = TeacherRemoveStudentJobApplicationCommand.builder()
				.jobApplicationId(jobApplicationId)
				.jobId(jobId)
				.build();
		AbstractCommandExecutor<TeacherRemoveStudentJobApplicationCommand, Job> commandExecutor = commandExecutorFactory.produceCommandExecutor(command);
		Job aggregate = commandExecutor.execute(command);
	}

	public void deleteJob(String jobId) throws Throwable {
		TeacherDeleteJobCommand command = TeacherDeleteJobCommand.builder()
				.jobId(jobId)
				.build();
		AbstractCommandExecutor<TeacherDeleteJobCommand, Job> commandExecutor = commandExecutorFactory.produceCommandExecutor(command);
		Job aggregate = commandExecutor.execute(command);
	}
}
