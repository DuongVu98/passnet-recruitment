package com.iucse.passnet.recruitment.domain.exceptions;

public class JobApplicationNotFound extends RuntimeException {

    public JobApplicationNotFound(String message) {
        super(message);
    }

    public JobApplicationNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
