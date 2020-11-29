package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.adapter.subscribers.CommandSubscriber;
import com.iucse.passnet.recruitment.adapter.subscribers.ViewUpdateSubscriber;
import com.iucse.passnet.recruitment.domain.annotation.Publisher;
import com.iucse.passnet.recruitment.domain.annotation.Subscriber;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.handlers.JobViewUpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.Observer;

@Configuration
public class ReactConfiguration {

    // Command gateway
    @Publisher(topic = "command-gateway")
    public CommandGateway getCommandGateway() {
        return new CommandGateway();
    }

    @Bean
    @Subscriber(topic = "command-gateway")
    public CommandSubscriber getCommandSubscriber() {
        return new CommandSubscriber();
    }

    // Domain event handler
    @Publisher(topic = "domain-event")
    public DomainEventBus getEventBus() {
        return new DomainEventBus();
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public ViewUpdateSubscriber getViewUpdateSubscriber() {
        return new ViewUpdateSubscriber();
    }
}
