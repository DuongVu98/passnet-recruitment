package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.grpc.RecruitmentSagaGateway;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherCreateClassroomCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.events.produce.CreateClassEvent;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherCreateClassroomCommandHandler extends AbstractJobAggregateCommandHandler<Job>{
    private TeacherCreateClassroomCommand command;
    private final RecruitmentSagaGateway recruitmentSagaGateway;

    public TeacherCreateClassroomCommandHandler(JobAggregateRepository aggregateRepository, EventTypes eventToApply, DomainEventBus eventBus, TeacherCreateClassroomCommand command, RecruitmentSagaGateway recruitmentSagaGateway) {
        super(aggregateRepository, eventToApply, eventBus);
        this.command = command;
        this.recruitmentSagaGateway = recruitmentSagaGateway;
    }

    @Override
    public DomainEvent execute() {
        Job job = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));
        List<String> taIds = job.getJobApplications().stream().map(jobApplication -> jobApplication.getId().getValue()).collect(Collectors.toList());
        recruitmentSagaGateway.produceCreateClassEvent(
           CreateClassEvent
               .builder()
               .teacherId(job.getJobOwner().getValue())
               .taIdList(taIds)
               .build()
        );

        this.aggregateRepository.delete(job);
    }
}
