package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.adapter.executor.SagaExecutor;
import com.iucse.passnet.recruitment.adapter.executor.QueryUpdateExecutor;
import com.iucse.passnet.recruitment.adapter.subscribers.CommandSubscriber;
import com.iucse.passnet.recruitment.domain.annotation.Publisher;
import com.iucse.passnet.recruitment.domain.annotation.Subscriber;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.events.IEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.Observer;

@Configuration
public class ReactConfiguration {

    @Publisher(topic = "domain-event")
    public EventBus<IEvent> getEventBus(){
        return new DomainEventBus();
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public Observer<IEvent> getQueryUpdateExecutor() {
        return new QueryUpdateExecutor();
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public Observer<IEvent> getSagaExecutor() {
        return new SagaExecutor();
    }

    @Publisher(topic = "command-gateway")
    public CommandGateway getCommandGateway() {
        return new CommandGateway();
    }

    @Bean
    @Subscriber(topic = "command-gateway")
    public Observer<BaseCommand> getCommandSubscriber(){
        return new CommandSubscriber();
    }
}
