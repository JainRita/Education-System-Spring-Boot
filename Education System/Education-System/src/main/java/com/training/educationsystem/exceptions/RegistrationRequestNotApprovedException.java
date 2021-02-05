package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class RegistrationRequestNotApprovedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * 
 * @param message
 */
	public RegistrationRequestNotApprovedException(final String message) {
		super(message);
	}

}
