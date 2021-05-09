package com.iucse.passnet.recruitment.domain.exceptions;

public class WrongCommandTypeException extends RuntimeException {

	public WrongCommandTypeException(String message) {
		super(message);
	}

	public WrongCommandTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
