package com.iucse.passnet.recruitment.usecase.services;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UUIDGeneratorService {

	public UUID generate() {
		return UUID.randomUUID();
	}
}
