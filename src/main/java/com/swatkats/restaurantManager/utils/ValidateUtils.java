package com.swatkats.restaurantManager.utils;

import com.swatkats.restaurantManager.DTO.MenuItemData;
import com.swatkats.restaurantManager.DTO.OrderData;
import com.swatkats.restaurantManager.exception.InvalidRequestException;

public class ValidateUtils {
	
	public static void validateMenuRequest(MenuItemData request) throws InvalidRequestException {
		if(request==null 
				|| request.getName() == null || request.getName().equalsIgnoreCase("") 
				|| request.getPrice() == 0
				|| request.getType() == null || request.getType().equals("") 
				|| request.getInventoryList() == null || request.getInventoryList().size() == 0 ) {
			throw new InvalidRequestException("Invalid Data sent in request body"); 
		}
	}
	
	public static void validateId(Long id) throws InvalidRequestException {
		if(id == 0) {
			throw new InvalidRequestException("Menu Item with id: "+id+" not found"); 
		}
	}
	
	public static void validateOrderRequest(OrderData request) throws InvalidRequestException {
		if(request==null 
				|| request.getStatus() == null || request.getStatus().equalsIgnoreCase("") 
				|| request.getTableNo() == 0
				|| request.getMenuList() == null || request.getMenuList().size() == 0 ) {
			throw new InvalidRequestException("Invalid Data sent in request body"); 
		}
	}
	

}
