package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class ListEmptyException extends Exception {
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
	public ListEmptyException(final String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ListEmptyException [message=" + message + "]";
	}
	

}
