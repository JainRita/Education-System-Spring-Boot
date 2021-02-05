package com.training.educationsystem.exceptions;
/**
 * 
 * @author Aniket
 *
 */
public class EmailAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * EmailAlreadyExists Exception
	 */

	public EmailAlreadyExistsException() {
		super();
	}
	/**
	 * 
	 * @param message
	 */
	public EmailAlreadyExistsException(final String message) {
		super(message);
	}
}
