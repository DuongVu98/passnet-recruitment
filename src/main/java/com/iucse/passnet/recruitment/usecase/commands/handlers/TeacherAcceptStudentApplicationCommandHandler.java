package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherAcceptStudentApplicationCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final TeacherAcceptStudentJobApplicationCommand command;

    @Builder
    public TeacherAcceptStudentApplicationCommandHandler(JobAggregateRepository aggregateRepository, EventTypes eventToApply, DomainEventBus eventBus, DomainEvent domainEvent, TeacherAcceptStudentJobApplicationCommand command) {
        super(aggregateRepository, eventToApply, eventBus, domainEvent);
        this.command = command;
    }

    @Override
    public Job execute() {
        Job jobAggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(this.command.getJobId()));
        List<JobApplication> jobApplicationList = jobAggregate.getJobApplications().stream().filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(this.command.getJobApplicationId()))).collect(Collectors.toList());

        if (jobApplicationList.isEmpty()) {
            return null;
        } else {
            JobApplication jobApplication = jobApplicationList.get(0);
            jobAggregate.acceptJobApplication(jobApplication);

            Job aggregate = this.aggregateRepository.save(jobAggregate);
            this.domainEvent = new DomainEvent(this.getEventToApply(), aggregate, jobAggregate.getId());
            return this.aggregateRepository.save(jobAggregate);
        }
    }
}
