package com.iucse.passnet.recruitment.adapter.channel;

import rx.Observer;

public interface EventBus<T> {
	void send(T t);
	void subscribe(Observer<T> observer);
}
