package com.iucse.passnet.recruitment.usecase.services;

import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompensatingCommandBackupService {

    private final Map<String, CompensatingCommand> compensatingCommandStore = new HashMap<>();

    public void addToStore(String eventId, CompensatingCommand command){
        this.compensatingCommandStore.put(eventId, command);
    }

    public CompensatingCommand getFromStore(String eventId) {
        return this.compensatingCommandStore.get(eventId);
    }

    public void removeFromStore(String eventId){
        this.compensatingCommandStore.remove(eventId);
    }
}