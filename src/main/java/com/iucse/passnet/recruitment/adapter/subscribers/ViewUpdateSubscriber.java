package com.iucse.passnet.recruitment.adapter.subscribers;

import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.handlers.JobViewUpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observer;

public class ViewUpdateSubscriber implements Observer<DomainEvent> {

    private JobViewUpdateHandler jobViewUpdateHandler;


    public ViewUpdateSubscriber(@Autowired JobViewUpdateHandler jobViewRepository) {
        this.jobViewUpdateHandler = jobViewRepository;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(DomainEvent event) {
        this.jobViewUpdateHandler.handle(event);
    }
}
