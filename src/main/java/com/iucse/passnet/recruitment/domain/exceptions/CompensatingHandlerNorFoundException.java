package com.iucse.passnet.recruitment.domain.exceptions;

import lombok.Getter;

@Getter
public class CompensatingHandlerNorFoundException extends RuntimeException {

    public CompensatingHandlerNorFoundException(String message) {
        super(message);
    }

    public CompensatingHandlerNorFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
