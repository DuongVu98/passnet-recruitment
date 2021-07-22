package com.iucse.passnet.recruitment.usecase.services;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.executors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorProvider {
    private final JobAggregateRepository aggregateRepository;
    private final UUIDGeneratorService uuidGeneratorService;

    @Autowired
    public CommandExecutorProvider(JobAggregateRepository aggregateRepository, UUIDGeneratorService uuidGeneratorService) {
        this.aggregateRepository = aggregateRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    public ApplyJobExecutor produceApplyJobExecutor() {
        return ApplyJobExecutor
           .builder()
           .jobRepository(this.aggregateRepository)
           .uuidGeneratorService(this.uuidGeneratorService)
           .build();
    }

    public AcceptJobApplicationExecutor produceAcceptJobApplicationExecutor() {
        return AcceptJobApplicationExecutor.builder().jobRepository(this.aggregateRepository).build();
    }

    public PostJobExecutor producePostJobExecutor() {
        return PostJobExecutor
           .builder()
           .jobRepository(this.aggregateRepository)
           .uuidGeneratorService(this.uuidGeneratorService)
           .build();
    }

    public RemoveJobApplicationExecutor produceJobApplicationExecutor() {
        return RemoveJobApplicationExecutor.builder().jobRepository(this.aggregateRepository).build();
    }

    public DeleteJobExecutor produceDeleteJobExecutor() {
        return DeleteJobExecutor.builder().jobRepository(aggregateRepository).build();
    }
}
