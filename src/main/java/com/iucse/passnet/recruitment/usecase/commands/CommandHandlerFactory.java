package com.iucse.passnet.recruitment.usecase.commands;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.StudentApplyJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.TeacherPostJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerFactory {

    private final TestRepository jobAggregateRepository;

    @Autowired
    public CommandHandlerFactory(TestRepository jobAggregateRepository) {
        this.jobAggregateRepository = jobAggregateRepository;
    }

    public AbstractJobAggregateCommandHandler<Job> getJobAggregateCommandHandler(BaseCommand command) {

        if (command instanceof TeacherPostJobCommand){
            return this.getTeacherPostJobCommandHandler((TeacherPostJobCommand) command);
        } else {
            return null;
        }
    }

    private AbstractJobAggregateCommandHandler<Job> getTeacherPostJobCommandHandler(TeacherPostJobCommand command) {
        return TeacherPostJobCommandHandler.builder()
           .command(command)
           .aggregateRepository(this.jobAggregateRepository)
           .build();
    }

    private AbstractJobAggregateCommandHandler<Job> getStudentApplyJobCommandHandler(StudentApplyJobCommand command){
        return StudentApplyJobCommandHandler.builder()
           .aggregateRepository(this.jobAggregateRepository)
           .command(command)
           .build();
    }
}
