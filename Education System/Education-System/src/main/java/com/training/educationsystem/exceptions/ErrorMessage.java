package com.training.educationsystem.exceptions;
/**
 * 
 * @author Aniket
 *
 */
public class ErrorMessage {
	/**
	 * HTTP error code
	 */
	private Integer errorCode;
	/**
	 * Error Message
	 */
	private String message;
	
	public ErrorMessage() {
		super();
	}
/**
 * 
 * @param errorCode
 * @param message
 */
	public ErrorMessage(final Integer errorCode, final String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}
	/**
	 * 
	 * @param errorMessage
	 */

	public void setMessage(final String message) {
		this.message = message;
	}

}
