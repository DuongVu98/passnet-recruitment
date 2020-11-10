package com.iucse.passnet.recruitment.adapter.channel;

import com.iucse.passnet.recruitment.usecase.events.IEvent;
import rx.Observer;
import rx.subjects.PublishSubject;

public class DomainEventBus implements EventBus<IEvent> {

    private final PublishSubject<IEvent> bus = PublishSubject.create();

    @Override
    public void send(IEvent event) {
        this.bus.onNext(event);
    }

    @Override
    public void subscribe(Observer<IEvent> observer) {
        this.bus.subscribe(observer);
    }
}
