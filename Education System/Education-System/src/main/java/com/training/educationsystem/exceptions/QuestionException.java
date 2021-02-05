package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
/**
 * 
 * @author Gauri
 *
 */
public class QuestionException extends Exception{

private static final long serialVersionUID = 1L;
/**
 * 	
 * @param str
 */
	public QuestionException(final String str) {
		 super(str);
	 }
}
