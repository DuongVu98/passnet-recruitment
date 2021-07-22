package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.TeacherDeleteJobCommand;
import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import lombok.Builder;

import java.util.Optional;

public class DeleteJobExecutor implements CommandExecutor {
    private final JobAggregateRepository jobRepository;

    @Builder
    public DeleteJobExecutor(JobAggregateRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job execute(BaseCommand baseCommand) {
        if (baseCommand instanceof TeacherDeleteJobCommand) {
            TeacherDeleteJobCommand command = (TeacherDeleteJobCommand) baseCommand;

            Optional<Job> jobOptional = this.jobRepository.findById(new JobId(command.getJobId()));

            if (jobOptional.isPresent()) {
                Job aggregate = jobOptional.get();

                this.jobRepository.delete(aggregate);
                return aggregate;
            } else {
                throw new JobNotFoundException("job not found");
            }
        } else {
            throw new WrongCommandTypeException("command must be TeacherDeleteJob");
        }
    }
}
