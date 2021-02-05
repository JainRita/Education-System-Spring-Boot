package com.training.educationsystem.exceptions;
/**
 * 
 * @author Afeeda
 *
 */
public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Message
	 */
	public transient String message;
	/**
	 * 
	 * @param message
	 */
	public AlreadyExistsException(final String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "AlreadyExistsException [message=" + message + "]";
	}
	

}
