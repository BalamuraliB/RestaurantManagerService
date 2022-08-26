package com.swatkats.restaurantManager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantManagerExceptionHandler {
	
	
	    @ExceptionHandler(NoItemFoundException.class)
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public ResponseEntity<Map<String, String>> handleNoItemFoundException(NoItemFoundException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", e.getMessage());
	        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
	        return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(InvalidRequestException.class)
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", e.getMessage());
	        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
	        return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(UnavailableEntityException.class)
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Map<String, String>> handleUnavailableEntityException(UnavailableEntityException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", e.getMessage());
	        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
	        return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

}
