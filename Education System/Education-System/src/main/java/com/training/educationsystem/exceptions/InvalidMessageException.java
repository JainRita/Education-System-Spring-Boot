package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Aniket
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidMessageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public InvalidMessageException(final String message) {
		super(message);
	}
}