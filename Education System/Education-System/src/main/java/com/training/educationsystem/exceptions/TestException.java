package com.training.educationsystem.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
/**
 * 
 * @author Gauri
 *
 */
public class TestException extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param str
	 */
	public TestException(final String str) {
		 super(str);
	 }
	
}
