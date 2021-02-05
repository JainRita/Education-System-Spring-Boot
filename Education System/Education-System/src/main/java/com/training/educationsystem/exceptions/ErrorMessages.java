package com.training.educationsystem.exceptions;
/**
 * 
 * @author Afeeda
 *
 */
public class ErrorMessages {
/**
 * HTTP status
 */
   private String status;
   /**
    * message
    */
	private String message;
	/**
	 * 
	 * @param status
	 * @param message
	 */
	public ErrorMessages(final String status, final String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	
	
}
