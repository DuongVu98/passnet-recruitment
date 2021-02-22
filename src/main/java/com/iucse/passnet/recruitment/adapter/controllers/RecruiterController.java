package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.forms.JobCreationForm;
import com.iucse.passnet.recruitment.adapter.grpc.RecruitmentSagaGateway;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.events.produce.CreateClassEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterController {
	private final CommandGateway commandGateway;
	private final RecruitmentSagaGateway recruitmentSagaGateway;
	private final JobAggregateRepository jobAggregateRepository;

	@Autowired
	public RecruiterController(CommandGateway commandGateway, RecruitmentSagaGateway recruitmentSagaGateway, JobAggregateRepository jobAggregateRepository) {
		this.commandGateway = commandGateway;
		this.recruitmentSagaGateway = recruitmentSagaGateway;
		this.jobAggregateRepository = jobAggregateRepository;
	}

	public void postJob(JobCreationForm form, String teacherId) {
		BaseCommand command = TeacherPostJobCommand
			.builder()
			.jobOwnerId(teacherId)
			.content(form.getContent())
			.jobName(form.getJobTitle())
			.courseName(form.getCourseName())
			.requirement(form.getRequirement())
			.semester(form.getSemester())
			.build();
		this.commandGateway.send(command);
	}

	public void acceptJobApplication(String jobApplicationId, String jobId) {
		BaseCommand command = TeacherAcceptStudentJobApplicationCommand
			.builder()
			.jobApplicationId(jobApplicationId)
			.jobId(jobId)
			.build();
		this.commandGateway.send(command);
	}

	public void createClassroom(String jobId) {
		Job job = jobAggregateRepository.findByIdWithJobApplications(new JobId(jobId));
		List<String> taIds = job.getJobApplications().stream().map(jobApplication -> jobApplication.getId().getValue()).collect(Collectors.toList());
		recruitmentSagaGateway.produceCreateClassEvent(CreateClassEvent
			.builder()
			.teacherId(job.getJobOwner().getValue())
			.taIdList(taIds)
			.build()
		);
	}
}
