package com.iucse.passnet.recruitment.domain.exceptions;

import lombok.Getter;

@Getter
public class CommandExecutorNotFoundException extends Throwable {

	public CommandExecutorNotFoundException(String message) {
		super(message);
	}
}
