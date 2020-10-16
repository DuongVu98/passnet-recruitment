package com.iucse.passnet.recruitment.adapter.channel;

import com.iucse.passnet.recruitment.domain.events.Event;
import rx.Observer;
import rx.subjects.PublishSubject;

public class DomainEventBus implements EventBus<Event> {

    private final PublishSubject<Event> bus = PublishSubject.create();

    @Override
    public void send(Event event) {
        this.bus.onNext(event);
    }

    @Override
    public void subscribe(Observer<Event> observer) {
        this.bus.subscribe(observer);
    }
}
