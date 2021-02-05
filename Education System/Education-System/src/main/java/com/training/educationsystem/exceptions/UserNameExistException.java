package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class UserNameExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * UserNameExist Exception
 */
	public UserNameExistException() {
		super();
	}
	/**
	 * 
	 * @param message
	 */
	public UserNameExistException(final String message) {
		super(message);
	}
	
}
