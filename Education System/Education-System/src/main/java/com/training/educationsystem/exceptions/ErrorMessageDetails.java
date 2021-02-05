package com.training.educationsystem.exceptions;

import java.util.Date;
/**
 * 
 * @author
 *
 */
public class ErrorMessageDetails {
	/**
	 * date
	 */
	private Date timestamp;
	/**
	 * status
	 */
	private String status;
	/**
	 * message
	 */
	private String message;
	/**
	 * request
	 */
	private String request;
	/**
	 * 
	 * @param timestamp
	 * @param status
	 * @param message
	 * @param string
	 */
	public ErrorMessageDetails(final Date timestamp, final String status, final String message, final String string) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.request = string;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
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
	public String getRequest() {
		return request;
	}
	public void setRequest(final String request) {
		this.request = request;
	}
	
	
}
