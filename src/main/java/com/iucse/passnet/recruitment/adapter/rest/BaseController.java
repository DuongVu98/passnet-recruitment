package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.domain.helpers.ErrorObject;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

	protected ResponseEntity<?> notFound() {
		return new ResponseEntity<>(
			ErrorObject.builder().errorCode(HttpStatus.NOT_FOUND.toString()).errorDescription("Not Found").build(),
			HttpStatus.NOT_FOUND
		);
	}

	protected ResponseEntity<?> badRequest(Throwable throwable) {
		return new ResponseEntity<>(
			ErrorObject.builder().errorCode(HttpStatus.BAD_REQUEST.toString()).errorDescription(throwable.getMessage()).build(),
			HttpStatus.BAD_REQUEST
		);
	}

	protected ResponseEntity<?> ok() {
		return ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex
			.getBindingResult()
			.getAllErrors()
			.forEach(
				error -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				}
			);

		return errors;
	}
}
