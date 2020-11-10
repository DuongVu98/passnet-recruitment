package com.iucse.passnet.recruitment.usecase.task;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandTaskRunner implements Runnable{

    private final AbstractJobAggregateCommandHandler<Job> jobAbstractCommand;

    @Override
    public void run() {
        Job aggregate = jobAbstractCommand.execute();
        /**
         * TODO
         * push domain event with aggregate
         */
    }
}
