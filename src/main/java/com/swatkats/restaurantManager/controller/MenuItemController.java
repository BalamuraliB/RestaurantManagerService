package com.swatkats.restaurantManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swatkats.restaurantManager.DAO.MenuItem;
import com.swatkats.restaurantManager.DTO.MenuItemData;
import com.swatkats.restaurantManager.exception.InvalidRequestException;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.exception.UnavailableEntityException;
import com.swatkats.restaurantManager.service.MenuItemService;

@RestController
@RequestMapping("/menu")
public class MenuItemController {
	
	@Autowired
	MenuItemService menuItemService;
	
	@GetMapping
	public List<MenuItemData> getAllAvailableMenuItems() throws NoItemFoundException{
		return menuItemService.getAllAvailableMenuItems();
	}
	
	@GetMapping("/{id}")
	public MenuItemData getMenuItem(@PathVariable long id) throws InvalidRequestException, UnavailableEntityException {
		return menuItemService.getMenuItem(id);
	}
	
	@PostMapping
	public String saveMenu(@RequestBody MenuItemData request) throws InvalidRequestException {
		return menuItemService.saveMenuItem(request);
	}
	
	@PutMapping("/{id}")
	public String updateMenu(@PathVariable long id, @RequestBody MenuItemData request) throws InvalidRequestException {
		return menuItemService.updateMenuItem(id, request);
	}
	

}
