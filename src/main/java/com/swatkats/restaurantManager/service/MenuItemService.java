package com.swatkats.restaurantManager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatkats.restaurantManager.DAO.Inventory;
import com.swatkats.restaurantManager.DAO.InventoryMenuItem;
import com.swatkats.restaurantManager.DAO.MenuItem;
import com.swatkats.restaurantManager.DTO.InventoryMenuItemData;
import com.swatkats.restaurantManager.DTO.MenuItemData;
import com.swatkats.restaurantManager.exception.InvalidRequestException;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.exception.UnavailableEntityException;
import com.swatkats.restaurantManager.repository.InventoryMenuItemRepo;
import com.swatkats.restaurantManager.repository.InventoryRepo;
import com.swatkats.restaurantManager.repository.MenuItemRepo;
import com.swatkats.restaurantManager.utils.RestaurantManagerConstants;
import com.swatkats.restaurantManager.utils.ValidateUtils;

@Service
public class MenuItemService {
	
	@Autowired
	MenuItemRepo menuItemRepo;

	@Autowired
	InventoryRepo inventoryRepo;

	@Autowired
	InventoryMenuItemRepo inventoryMenuRepo;
	
	public List<MenuItemData> getAllAvailableMenuItems() throws NoItemFoundException{
		Optional<List<MenuItem>> menuItemList = menuItemRepo.findAllByAvailable(true);
		if(menuItemList.isEmpty() || menuItemList.get().size() == 0) {
			throw new NoItemFoundException("No Menu Items are available");
		}
		List<MenuItemData> responseList = new ArrayList<MenuItemData>();
		for(MenuItem menuItem : menuItemList.get()) {
			MenuItemData menuData = new MenuItemData(menuItem);
			responseList.add(menuData);
		}
		return responseList;
	}
	
	public MenuItemData getMenuItem(long id) throws InvalidRequestException, UnavailableEntityException {
		ValidateUtils.validateId(id);
		Optional<MenuItem> menuItem = menuItemRepo.findById(id);
		if(menuItem.isEmpty()) {
			throw new InvalidRequestException("No Menu with id: "+id+" found");
		}
		if(!menuItem.get().isAvailable()) {
			throw new UnavailableEntityException("Menu with id: "+id+" is currently unavailable");
		}
		MenuItemData response = translateMenuItem(menuItem.get());
		return response;
	}
	
	public String saveMenuItem(MenuItemData request) throws InvalidRequestException{
		ValidateUtils.validateMenuRequest(request);
		MenuItem menuItem = new MenuItem(request);
		processInventoryList(request.getInventoryList(), menuItem);
		menuItemRepo.save(menuItem);
		inventoryMenuRepo.saveAll(menuItem.getInventoryList());
		return RestaurantManagerConstants.SAVE_DATA_SUCCESS;
	}
	
	public String updateMenuItem(long id, MenuItemData request) throws InvalidRequestException{
		ValidateUtils.validateId(id);
		ValidateUtils.validateMenuRequest(request);
		Optional<MenuItem> menuItem = menuItemRepo.findById(id);
		if(menuItem.isEmpty()) {
			throw new InvalidRequestException("No Menu with id: "+id+" found");
		}
		menuItem.get().setAvailable(request.isAvailable());
		menuItem.get().setName(request.getName());
		menuItem.get().setPrice(request.getPrice());
		menuItem.get().setType(request.getType());
		processInventoryList(request.getInventoryList(), menuItem.get());
		menuItemRepo.save(menuItem.get());
		inventoryMenuRepo.saveAll(menuItem.get().getInventoryList());
		return RestaurantManagerConstants.UPDATE_DATA_SUCCESS;
	}
	
	public void processInventoryList(List<InventoryMenuItemData> requestList, MenuItem menuItem) throws InvalidRequestException {
		List<InventoryMenuItem> invMenuList = new ArrayList<InventoryMenuItem>();
		for(InventoryMenuItemData inventoryItem : requestList) {
			if(inventoryItem.getId() != 0) {
				Optional<InventoryMenuItem> invMenu = inventoryMenuRepo.findById(inventoryItem.getId());
				if(invMenu.isEmpty()) {
					throw new InvalidRequestException("No inventory menu correlation with id: "+inventoryItem.getId()+" found");
				}
				invMenu.get().setUnits(inventoryItem.getUnits());
				invMenuList.add(invMenu.get());
			} else {
				Optional<Inventory> inventory = inventoryRepo.findById(inventoryItem.getInventoryId());
				if(inventory.isEmpty()) {
					throw new InvalidRequestException("No inventory with id: "+inventoryItem.getInventoryId()+" found");
				}
				InventoryMenuItem invMenu = new InventoryMenuItem();
				invMenu.setInventory(inventory.get());
				invMenu.setMenuItem(menuItem);
				invMenu.setUnits(inventoryItem.getUnits());
				invMenuList.add(invMenu);
			}
		}
		if(invMenuList.size() == 0) {
			throw new InvalidRequestException("No Valid Inventories found for menu request");
		}
		menuItem.setInventoryList(invMenuList);
	}

	public MenuItemData translateMenuItem(MenuItem menuItem) {
		MenuItemData menuItemData = new MenuItemData(menuItem);
		List<InventoryMenuItemData> menuItemList = new ArrayList<InventoryMenuItemData>();
		for(InventoryMenuItem itemData : menuItem.getInventoryList()) {
			InventoryMenuItemData itemResponse = new InventoryMenuItemData();
			itemResponse.setId(itemData.getId());
			itemResponse.setInventoryId(itemData.getInventory().getId());
			itemResponse.setInventoryName(itemData.getInventory().getName());
			itemResponse.setUnits(itemData.getUnits());
			menuItemList.add(itemResponse);
		}
		menuItemData.setInventoryList(menuItemList);
		return menuItemData;
		
	}
}
