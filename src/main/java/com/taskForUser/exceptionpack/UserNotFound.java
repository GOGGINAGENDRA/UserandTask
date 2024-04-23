package com.taskForUser.exceptionpack;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

	@SuppressWarnings("unused")
	private String message;

	public UserNotFound(String message) {
		super(message);
		this.message = message;
	}

}
