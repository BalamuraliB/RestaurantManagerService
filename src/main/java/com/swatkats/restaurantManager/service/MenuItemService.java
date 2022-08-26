package com.swatkats.restaurantManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatkats.restaurantManager.DAO.InventoryMenuItem;
import com.swatkats.restaurantManager.DAO.MenuItem;
import com.swatkats.restaurantManager.DTO.MenuItemRequest;
import com.swatkats.restaurantManager.exception.InvalidRequestException;
import com.swatkats.restaurantManager.repository.InventoryMenuItemRepo;
import com.swatkats.restaurantManager.repository.InventoryRepo;
import com.swatkats.restaurantManager.repository.MenuItemRepo;
import com.swatkats.restaurantManager.utils.RestaurantManagerConstants;

@Service
public class MenuItemService {
	
	@Autowired
	MenuItemRepo menuItemRepo;

	@Autowired
	InventoryRepo inventoryRepo;

	@Autowired
	InventoryMenuItemRepo inventoryMenuRepo;
	
	public List<MenuItem> getAllAvailableMenuItems(){
		List<MenuItem> menuItem = null;
		return menuItem;
	}
	
	public MenuItem getMenuItem(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String saveMenuItem(MenuItemRequest request) throws InvalidRequestException{
		validateMenuRequest(request);
		
		//validate inventory
		//createlist
		//save
		return RestaurantManagerConstants.SAVE_DATA_SUCCESS;
	}
	
	public String updateMenuItem(long id, MenuItemRequest request) throws InvalidRequestException{
		validateId(id);
		validateMenuRequest(request);
		return RestaurantManagerConstants.UPDATE_DATA_SUCCESS;
	}
	
	public void validateMenuRequest(MenuItemRequest request) throws InvalidRequestException {
		if(request==null 
				|| request.getName() == null || request.getName().equalsIgnoreCase("") 
				|| request.getPrice() == 0
				|| request.getType() == null || request.getType().equals("") 
				|| request.getInventoryList() == null || request.getInventoryList().size() == 0 ) {
			throw new InvalidRequestException("Invalid Data sent in request body"); 
		}
	}
	
	public void validateId(Long id) throws InvalidRequestException {
		if(id == 0) {
			throw new InvalidRequestException("Invalid id sent in request"); 
		}
	}
	
	public void validateInventoryList(List<Long> inventoryList) {
		//invento
	}

}
