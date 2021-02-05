package com.training.educationsystem.exceptions;
/**
 * 
 * @author Gauri
 *
 */
public class InvalidStudyMaterialException extends Exception{
	private static final long serialVersionUID = 1L;
	/**
	 * message
	 */
	public transient String str;
	/**
	 * 
	 * @param str
	 */
	public InvalidStudyMaterialException(final String str) {
		super();
		this.str = str;
	}
	@Override
	public String toString() {
		return "InvalidStudyMaterialException [str=" + str + "]";
	}

}
