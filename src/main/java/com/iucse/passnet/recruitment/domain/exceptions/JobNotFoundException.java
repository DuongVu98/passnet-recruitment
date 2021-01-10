package com.iucse.passnet.recruitment.domain.exceptions;

public class JobNotFoundException extends RuntimeException {

	public JobNotFoundException(String message) {
		super(message);
	}
}
