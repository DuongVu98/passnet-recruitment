package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;

public interface IEventHandler {
	void handle(DomainEvent event);
}
