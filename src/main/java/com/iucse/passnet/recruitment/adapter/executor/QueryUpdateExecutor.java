package com.iucse.passnet.recruitment.adapter.executor;

import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import rx.Observer;

public class QueryUpdateExecutor implements Observer<DomainEvent> {

	@Override
	public void onCompleted() {}

	@Override
	public void onError(Throwable e) {}

	@Override
	public void onNext(DomainEvent event) {}
}
