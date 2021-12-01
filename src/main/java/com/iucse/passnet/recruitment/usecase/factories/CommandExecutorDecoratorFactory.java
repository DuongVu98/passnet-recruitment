package com.iucse.passnet.recruitment.usecase.factories;

import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.usecase.decorators.CommandExecutorDecoratorTypes;
import com.iucse.passnet.recruitment.usecase.decorators.CompensatingBackupDecorator;
import com.iucse.passnet.recruitment.usecase.decorators.EventPublisherDecorator;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class CommandExecutorDecoratorFactory {
    private final CompensatingCommandBackupService compensatingBackupService;
    private final CompensatingCommandProvider compensatingCommandProvider;
    private final JobApplicationRepository jobApplicationRepository;
    private final HttpServletRequest request;
    private final ApplicationEventPublisher eventPublisher;

    public CommandExecutor produce(CommandExecutor commandExecutor, CommandExecutorDecoratorTypes decorator) {
        switch (decorator) {
            case COMPENSATING_COMMAND_BACKUP:
                return this.produceCompensatingBackupDecorator(commandExecutor);
            case EVENT_PUBLISHER:
                return this.produceEventPublisherDecorator(commandExecutor);
            default:
                return commandExecutor;
        }
    }

    private CommandExecutor produceCompensatingBackupDecorator(CommandExecutor commandExecutor) {
        return CompensatingBackupDecorator
           .builder()
           .request(this.request)
           .commandExecutor(commandExecutor)
           .compensatingCommandProvider(this.compensatingCommandProvider)
           .compensatingBackupService(this.compensatingBackupService)
           .build();
    }

    private CommandExecutor produceEventPublisherDecorator(CommandExecutor commandExecutor) {
        return EventPublisherDecorator
           .builder()
           .request(this.request)
           .commandExecutor(commandExecutor)
           .jobApplicationRepository(this.jobApplicationRepository)
           .eventPublisher(this.eventPublisher)
           .build();
    }
}
