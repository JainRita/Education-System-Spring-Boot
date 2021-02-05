package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * message
 */
	public transient String message;
/**
 * 
 * @param message
 */
	public NotFoundException(final String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "NotFoundException [message=" + message + "]";
	}
     
	
	
}
