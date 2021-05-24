package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.AcceptJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.RemoveJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherDeleteJobCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.domain.forms.JobCreationForm;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorDecoratorFactory;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruiterController {
	private final CommandExecutorFactory commandExecutorFactory;
	private final CommandExecutorDecoratorFactory commandExecutorDecoratorFactory;

	@Autowired
	public RecruiterController(CommandExecutorFactory commandExecutorFactory, CommandExecutorDecoratorFactory commandExecutorDecoratorFactory) {
		this.commandExecutorFactory = commandExecutorFactory;
		this.commandExecutorDecoratorFactory = commandExecutorDecoratorFactory;
	}

	public void postJob(JobCreationForm form, String teacherId) {
		TeacherPostJobCommand command = TeacherPostJobCommand
			.builder()
			.jobOwnerId(teacherId)
			.content(form.getContent())
			.jobName(form.getJobTitle())
			.courseName(form.getCourseName())
			.requirement(form.getRequirement())
			.semester(form.getSemester())
			.build();

		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		Job aggregate = commandExecutor.execute(command);
	}

	//	@CacheEvict(value = ViewTypes.JOB_VIEW, key = "#jobId")
	public void acceptJobApplication(String jobApplicationId, String jobId) {
		AcceptJobApplicationCommand command = AcceptJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();

		//		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		//		CommandExecutor commandExecutor1 = commandExecutorDecoratorFactory.produce(commandExecutor, CommandExecutorDecoratorTypes.COMPENSATING_COMMAND_BACKUP);
		//		Job aggregate = commandExecutor1.execute(command);

		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		Job aggregate = commandExecutor.execute(command);
	}

	//	@CacheEvict(value = ViewTypes.JOB_VIEW, key = "#jobId")
	public void removeJobApplication(String jobApplicationId, String jobId) {
		RemoveJobApplicationCommand command = RemoveJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();
		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		Job aggregate = commandExecutor.execute(command);
	}

	//	@CacheEvict(value = ViewTypes.JOB_VIEW, key = "#jobId")
	public void deleteJob(String jobId) {
		TeacherDeleteJobCommand command = TeacherDeleteJobCommand.builder().jobId(jobId).build();
		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		Job aggregate = commandExecutor.execute(command);
	}
}
