package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.domain.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[ApplicatorController]")
public class ApplicatorController {
	private final CommandExecutorFactory commandExecutorFactory;

	@Autowired
	public ApplicatorController(CommandExecutorFactory commandExecutorFactory) {
		this.commandExecutorFactory = commandExecutorFactory;
	}

	@CacheEvict(value = "job-view", key = "#jobId")
	public void studentApplyJob(JobApplicationForm jobApplicationForm, String studentId, String jobId) {
		StudentApplyJobCommand command = StudentApplyJobCommand
			.builder()
			.jobId(jobId)
			.studentId(studentId)
			.content(jobApplicationForm.getContent())
			.letter(jobApplicationForm.getLetter())
			.build();

		CommandExecutor commandExecutor = commandExecutorFactory.produce(command);
		Job aggregate = commandExecutor.execute(command);
	}
}
