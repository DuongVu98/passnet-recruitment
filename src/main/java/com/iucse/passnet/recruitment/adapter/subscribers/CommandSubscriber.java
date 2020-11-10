package com.iucse.passnet.recruitment.adapter.subscribers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.commands.CommandHandlerFactory;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.task.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observer;

public class CommandSubscriber implements Observer<BaseCommand> {

    @Autowired
    private CommandHandlerFactory commandHandlerFactory;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(BaseCommand command) {
        AbstractJobAggregateCommandHandler<Job> commandHandler = this.commandHandlerFactory.getJobAggregateCommandHandler(command);
        new Thread(new CommandExecutor(commandHandler)).start();
    }
}
