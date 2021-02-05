package com.training.educationsystem.exceptions;
/**
 * 
 * @author Aniket
 *
 */
public class DateException extends Exception{
	private static final long serialVersionUID = 1L;
	/**
	 * Sy=tring message
	 */
	public transient String str;
	/**
	 * 
	 * @param str
	 */
	public DateException(final String str) {
		super();
		this.str = str;
	}
	@Override
	public String toString() {
		return "DateException [str=" + str + "]";
	}
	


}
