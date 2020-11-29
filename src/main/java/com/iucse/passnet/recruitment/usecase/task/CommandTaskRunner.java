package com.iucse.passnet.recruitment.usecase.task;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j(topic = "[CommandTaskRunner]")
public class CommandTaskRunner implements Runnable{

    private final AbstractJobAggregateCommandHandler<Job> jobAbstractCommandHandler;

    @Override
    public void run() {
        DomainEvent domainEvent = jobAbstractCommandHandler.execute();
        jobAbstractCommandHandler.getEventBus().send(domainEvent);
    }
}
