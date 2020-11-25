package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import lombok.Getter;

@Getter
public abstract class AbstractEventHandler {
    protected JobViewRepository jobViewRepository;

    abstract void handle();
}
