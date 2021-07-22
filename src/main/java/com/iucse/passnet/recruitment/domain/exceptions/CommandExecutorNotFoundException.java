package com.iucse.passnet.recruitment.domain.exceptions;

import lombok.Getter;

@Getter
public class CommandExecutorNotFoundException extends RuntimeException {

    public CommandExecutorNotFoundException(String message) {
        super(message);
    }

    public CommandExecutorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
