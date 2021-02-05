package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class StudentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * StudentNotFound Exception
 */
	public StudentNotFoundException() {
		super();

	}
/**
 * 
 * @param message
 */
	public StudentNotFoundException(final String message) {
		super(message);

	}

}
