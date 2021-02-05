package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class PaymentException extends Exception{

	private static final long serialVersionUID = 1L;
	/**
	 * message
	 */
	public transient  String str;
	/**
	 * 
	 * @param str
	 */
	public PaymentException(final String str) {
		super();
		this.str = str;
	}
	@Override
	public String toString() {
		return "PaymentException [str=" + str + "]";
	}
	
}
