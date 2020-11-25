package com.iucse.passnet.recruitment.usecase.task;

import com.iucse.passnet.recruitment.usecase.events.events.EventPayload;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViewUpdateRunner implements Runnable {

    private EventPayload event;

    @Override
    public void run() {

    }
}
