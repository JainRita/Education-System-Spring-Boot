package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class TrainerNotFoundException extends Exception {
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
	public TrainerNotFoundException(final String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "TrainerNotFoundException [message=" + message + "]";
	}
	

}
