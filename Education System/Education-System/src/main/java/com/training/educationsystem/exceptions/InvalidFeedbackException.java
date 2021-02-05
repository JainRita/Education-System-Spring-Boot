package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class InvalidFeedbackException extends Exception {

/**
 * InvalidInputException
 */
	private static final long serialVersionUID=1L;
	/**
	 * message
	 */
	public transient String message;
	/**
	 * 
	 * @param message
	 */
	public InvalidFeedbackException(final String message) {
		super();
		this.message=message;
}
	@Override
	public String toString() {
		return "InvalidFeedbackException [message=" + message + "]";
	}
	
}
