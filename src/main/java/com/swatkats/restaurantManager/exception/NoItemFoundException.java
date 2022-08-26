package com.swatkats.restaurantManager.exception;

import org.springframework.stereotype.Component;

@Component
public class NoItemFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoItemFoundException() {
        super();
    }
    public NoItemFoundException(String message) {
        super(message);
    }
    
       
}
