package com.swatkats.restaurantManager.exception;

import org.springframework.stereotype.Component;

@Component
public class InvalidRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidRequestException() {
        super();
    }
    public InvalidRequestException(String message) {
        super(message);
    }
    
       
}
