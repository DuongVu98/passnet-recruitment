package com.iucse.passnet.recruitment.adapter.channel;

import com.iucse.passnet.recruitment.usecase.events.events.EventPayload;
import rx.Observer;
import rx.subjects.PublishSubject;

public class DomainEventBus implements EventBus<EventPayload> {

    private final PublishSubject<EventPayload> bus = PublishSubject.create();

    @Override
    public void send(EventPayload event) {
        this.bus.onNext(event);
    }

    @Override
    public void subscribe(Observer<EventPayload> observer) {
        this.bus.subscribe(observer);
    }
}
