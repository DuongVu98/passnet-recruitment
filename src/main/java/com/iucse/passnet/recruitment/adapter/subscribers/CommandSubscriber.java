package com.iucse.passnet.recruitment.adapter.subscribers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.commands.CommandHandlerFactory;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.task.CommandTaskRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observer;

@Slf4j(topic = "[CommandSubscriber]")
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
        AbstractJobAggregateCommandHandler<Job> commandHandler = this.getJobAggregateCommandHandler(command);
        new Thread(new CommandTaskRunner(commandHandler)).start();
    }

    private AbstractJobAggregateCommandHandler<Job> getJobAggregateCommandHandler(BaseCommand command) {
        if (command instanceof TeacherPostJobCommand) {
            return this.commandHandlerFactory.getTeacherPostJobCommandHandler((TeacherPostJobCommand) command);
        } else if (command instanceof StudentApplyJobCommand) {
            return this.commandHandlerFactory.getStudentApplyJobCommandHandler((StudentApplyJobCommand) command);
        } else if (command instanceof TeacherAcceptStudentJobApplicationCommand) {
            return this.commandHandlerFactory.getTeacherAcceptStudentJobApplicationCommandHandler((TeacherAcceptStudentJobApplicationCommand) command);
        } else {
            return null;
        }
    }
}
