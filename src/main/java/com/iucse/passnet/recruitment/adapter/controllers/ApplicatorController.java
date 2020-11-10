package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.interactors.InteractorFactory;
import com.iucse.passnet.recruitment.usecase.interactors.queries.ActionQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[ApplicatorController]")
public class ApplicatorController {

    private final InteractorFactory interactorFactory;

    private final CommandGateway commandGateway;

    @Autowired
    public ApplicatorController(InteractorFactory interactorFactory, CommandGateway commandGateway) {
        this.interactorFactory = interactorFactory;
        this.commandGateway = commandGateway;
    }

    public void studentApplyJob(JobApplicationForm formString, String studentId, String jobId){
        BaseCommand command = StudentApplyJobCommand.builder()
           .jobId(jobId)
           .studentId(studentId)
           .build();

        this.commandGateway.send(command);
    }

    public Teacher viewTeacherProfile(String teacherId){
        ActionQuery<Teacher> query = this.interactorFactory.getViewTeacherProfile(teacherId);
        return query.execute();
    }

    public Job viewPostedJob(String jobId){
        ActionQuery<Job> query = this.interactorFactory.getViewPostedJob(jobId);
        return query.execute();
    }
}
