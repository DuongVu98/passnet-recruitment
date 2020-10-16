package com.iucse.passnet.recruitment.configuration;

import com.iucse.passnet.recruitment.adapter.channel.EventBus;
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
        return null;
    }

    @Bean
    @Subscriber(topic = "domain-event")
    public Observer<Event> getQuerySubscriber() {
        return null;
    }
}
