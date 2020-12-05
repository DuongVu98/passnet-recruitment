package com.iucse.passnet.recruitment.adapter.subscribers;

import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.handlers.JobApplicationViewUpdateHandler;
import com.iucse.passnet.recruitment.usecase.events.handlers.JobViewUpdateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observer;

@Slf4j(topic = "[ViewUpdateSubscriber]")
public class ViewUpdateSubscriber implements Observer<DomainEvent> {
	@Autowired
	private JobViewUpdateHandler jobViewUpdateHandler;

	@Autowired
	private JobApplicationViewUpdateHandler jobApplicationViewUpdateHandler;

	@Override
	public void onCompleted() {}

	@Override
	public void onError(Throwable e) {}

	@Override
	public void onNext(DomainEvent event) {
		this.jobViewUpdateHandler.handle(event);
		this.jobApplicationViewUpdateHandler.handle(event);
	}
}
