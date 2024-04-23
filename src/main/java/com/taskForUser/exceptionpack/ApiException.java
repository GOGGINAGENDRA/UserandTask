package com.taskForUser.exceptionpack;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException {

	@SuppressWarnings("unused")
	private String message;

	public ApiException(String message) {
		super(message);
		this.message = message;
	}

}
