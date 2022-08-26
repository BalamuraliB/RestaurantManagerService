package com.swatkats.restaurantManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatkats.restaurantManager.DAO.Inventory;
import com.swatkats.restaurantManager.DTO.InventoryData;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.repository.InventoryRepo;
import com.swatkats.restaurantManager.utils.RestaurantManagerConstants;

@Service
public class InventoryService {
	
	@Autowired
	InventoryRepo inventoryRepo;
	
	public List<Inventory> getInventoryList() throws NoItemFoundException, Exception{
		List<Inventory> inventoryList = inventoryRepo.findAll();
			if(inventoryList.size() == 0) {
				throw new NoItemFoundException("Inventory Item not found");
			}
		return inventoryList;
	}

	public String save(Inventory inventory) {
		inventoryRepo.save(inventory);
		return RestaurantManagerConstants.SAVE_DATA_SUCCESS;
	}

	public String update(InventoryData inventoryData, long id) throws NoItemFoundException {
		Optional<Inventory> inventory = inventoryRepo.findById(id);
		if(inventory.isEmpty()) {
			throw new NoItemFoundException("Inventory Item with id "+id+" found");
		}
		inventory.get().setName(inventoryData.getName());
		inventory.get().setUnitsAvailable(inventoryData.getUnitsAvailable());
		inventoryRepo.save(inventory.get());
		return  RestaurantManagerConstants.UPDATE_DATA_SUCCESS;
	}

}
