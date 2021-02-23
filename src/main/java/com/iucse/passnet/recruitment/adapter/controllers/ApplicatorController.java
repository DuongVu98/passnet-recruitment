package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.executors.AbstractCommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[ApplicatorController]")
public class ApplicatorController {

	private final CommandExecutorFactory commandExecutorFactory;

	@Autowired
	public ApplicatorController(CommandExecutorFactory commandExecutorFactory) {
		this.commandExecutorFactory = commandExecutorFactory;
	}

	public void studentApplyJob(JobApplicationForm jobApplicationForm, String studentId, String jobId) throws Throwable {
		StudentApplyJobCommand command = StudentApplyJobCommand.builder()
			.jobId(jobId)
			.studentId(studentId)
			.content(jobApplicationForm.getContent())
			.letter(jobApplicationForm.getLetter())
			.build();

		AbstractCommandExecutor<StudentApplyJobCommand, Job> commandExecutor = commandExecutorFactory.produceCommandExecutor(command);
		Job aggregate = commandExecutor.execute(command);
	}
}
