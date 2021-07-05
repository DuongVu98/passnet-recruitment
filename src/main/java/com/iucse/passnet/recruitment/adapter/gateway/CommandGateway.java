package com.iucse.passnet.recruitment.adapter.gateway;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.domain.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.forms.JobCreationForm;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorDecoratorFactory;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {
    private final CommandExecutorFactory commandExecutorFactory;
    private final CommandExecutorDecoratorFactory commandExecutorDecoratorFactory;

    @Autowired
    public CommandGateway(CommandExecutorFactory commandExecutorFactory, CommandExecutorDecoratorFactory commandExecutorDecoratorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.commandExecutorDecoratorFactory = commandExecutorDecoratorFactory;
    }

    public void postJob(JobCreationForm form, String teacherId) {
        TeacherPostJobCommand command = TeacherPostJobCommand.builder()
           .jobOwnerId(teacherId)
           .content(form.getContent())
           .jobName(form.getJobTitle())
           .courseName(form.getCourseName())
           .requirement(form.getRequirement())
           .semester(form.getSemester())
           .build();

        var commandExecutor = commandExecutorFactory.produce(command);
        var aggregate = commandExecutor.execute(command);
    }

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

    public void acceptJobApplication(String jobApplicationId, String jobId) {
        AcceptJobApplicationCommand command = AcceptJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();
        var commandExecutor = commandExecutorFactory.produce(command);
        var aggregate = commandExecutor.execute(command);
    }

    public void removeJobApplication(String jobApplicationId, String jobId) {
        RemoveJobApplicationCommand command = RemoveJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();
        var commandExecutor = commandExecutorFactory.produce(command);
        var aggregate = commandExecutor.execute(command);
    }

    public void deleteJob(String jobId) {
        TeacherDeleteJobCommand command = TeacherDeleteJobCommand.builder().jobId(jobId).build();
        var commandExecutor = commandExecutorFactory.produce(command);
        var aggregate = commandExecutor.execute(command);
    }
}
