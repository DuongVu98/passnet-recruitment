package com.iucse.passnet.recruitment.domain.exceptions;

public class NullIdentifierException extends RuntimeException {

	public NullIdentifierException(String message) {
		super(message);
	}

	public NullIdentifierException(String message, Throwable cause) {
		super(message, cause);
	}
}
