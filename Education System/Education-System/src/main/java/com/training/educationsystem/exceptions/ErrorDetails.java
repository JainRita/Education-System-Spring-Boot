package com.training.educationsystem.exceptions;
/**
 * 
 * @author Afeeda
 *
 */
public class ErrorDetails {
	/**
	 * a string message
	 */
	private String message;
	/**
	 * a string details
	 */
	private String details;
	/**
	 * 
	 * @param message
	 * @param details
	 */
	public ErrorDetails(final String message, final String details) {
		
		super();
		this.message = message;
		this.details = details;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(final String details) {
		this.details = details;
	}
	
}
