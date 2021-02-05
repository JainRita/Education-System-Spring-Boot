package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Gauri
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidProgressException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public InvalidProgressException(final String message) {
		super(message);
	}
}