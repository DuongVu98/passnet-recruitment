package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.adapter.executor.SagaExecutor;
import com.iucse.passnet.recruitment.adapter.executor.QueryUpdateExecutor;
import com.iucse.passnet.recruitment.domain.annotation.Publisher;
import com.iucse.passnet.recruitment.domain.annotation.Subscriber;
import com.iucse.passnet.recruitment.domain.events.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.Observer;

@Configuration
public class ReactConfiguration {

    @Publisher(topic = "domain-event")
    public EventBus<Event> getEventBus(){
        return new DomainEventBus();
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public Observer<Event> getQueryUpdateExecutor() {
        return new QueryUpdateExecutor();
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public Observer<Event> getSagaExecutor() {
        return new SagaExecutor();
    }
}
