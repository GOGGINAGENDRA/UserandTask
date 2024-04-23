package com.taskForUser.exceptionpack;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFound extends RuntimeException {

	@SuppressWarnings("unused")
	private String message;

	public TaskNotFound(String message) {
		super(message);
		this.message = message;
	}

}
