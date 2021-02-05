package com.training.educationsystem.exceptions;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
/**
 * 
 * @author 
 *
 */
public class ErrorResponse {
	
	/**
	 * General error message about nature of error
	 */
    private String message; 
 
    /**
	 * Specific errors in API request processing
	 */
    private String status;  

    /**
     * 
     * @param message
     * @param status
     */
	public ErrorResponse(final String message, final String status) {  
		super();
		this.message = message;
		this.status = status;
	}
	
	/**
	 * Getter and Setter
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

    
    
}
