package com.iucse.passnet.recruitment.adapter.channel;

import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import rx.Observer;
import rx.subjects.PublishSubject;

public class DomainEventBus implements EventBus<DomainEvent> {

    private final PublishSubject<DomainEvent> bus = PublishSubject.create();

    @Override
    public void send(DomainEvent event) {
        this.bus.onNext(event);
    }

    @Override
    public void subscribe(Observer<DomainEvent> observer) {
        this.bus.subscribe(observer);
    }
}
