package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class InvalidContactNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * InvalidContactNumber Exception
 */
	public InvalidContactNumberException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param message
	 */
	public InvalidContactNumberException(final String message) {
		super(message);
		
	}
	
}
