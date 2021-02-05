package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
/**
 * 
 * @author Gauri
 *
 */
public class InvalidAdminException extends RuntimeException{

	private static final long serialVersionUID = 1L;
/**
 * 
 * @param message
 */
	public InvalidAdminException(final String message) {
		super(message);
	}
}