package com.iucse.passnet.recruitment.usecase.task;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandTaskRunner implements Runnable{

    private final AbstractJobAggregateCommandHandler<Job> jobAbstractCommandHandler;

    @Override
    public void run() {
        Job aggregate = jobAbstractCommandHandler.execute();
        jobAbstractCommandHandler.getEventBus().send(new DomainEvent(jobAbstractCommandHandler.getEventToApply(), aggregate));
    }
}
