package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class InvalidPaymentException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * message
	 */
	public transient String str;
	/**
	 * 
	 * @param str
	 */
	public InvalidPaymentException(final String str) {
		super();
		this.str = str;
	}
	@Override
	public String toString() {
		return "InvalidPaymentException [str=" + str + "]";
	}
	

}
