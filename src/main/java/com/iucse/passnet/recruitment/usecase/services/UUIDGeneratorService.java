package com.iucse.passnet.recruitment.usecase.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDGeneratorService {

    public UUID generate() {
        return UUID.randomUUID();
    }
}
