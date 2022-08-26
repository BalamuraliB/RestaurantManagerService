package com.swatkats.restaurantManager.exception;

import org.springframework.stereotype.Component;

@Component
public class UnavailableEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnavailableEntityException() {
        super();
    }
    public UnavailableEntityException(String message) {
        super(message);
    }
    
       
}
