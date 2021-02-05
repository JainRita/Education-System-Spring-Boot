package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class InvalidCourseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * message
	 */
	public transient String str;
	/**
	 * 
	 * @param message
	 */
	public InvalidCourseException(final String message)
	{
		super();
		this.str=message;
	}
	@Override
	public String toString() {
		return "InvalidCourseException [" + str + "]";
	}
	
}
