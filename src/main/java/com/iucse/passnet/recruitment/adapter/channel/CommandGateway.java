package com.iucse.passnet.recruitment.adapter.channel;

import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import rx.Observer;
import rx.subjects.PublishSubject;

public class CommandGateway implements EventBus<BaseCommand> {
	private final PublishSubject<BaseCommand> bus = PublishSubject.create();

	@Override
	public void send(BaseCommand command) {
		this.bus.onNext(command);
	}

	@Override
	public void subscribe(Observer<BaseCommand> observer) {
		this.bus.subscribe(observer);
	}
}
