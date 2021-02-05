package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
/**
 * 
 * @author 
 *
 */
public class EmptyInputException extends Exception {
	private static final long serialVersionUID = 1L;
/**
 * 	
 * @param str
 */
	public EmptyInputException(final String str) {
		 super(str);
	 }

}
