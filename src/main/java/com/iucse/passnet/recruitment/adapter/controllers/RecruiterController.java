package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.forms.JobCreationForm;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.executors.AbstractCommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void createClassroom(String jobId) {}
}
