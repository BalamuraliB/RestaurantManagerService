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

import com.swatkats.restaurantManager.DAO.Inventory;
import com.swatkats.restaurantManager.DTO.InventoryData;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@GetMapping
	public List<Inventory> getAllInventory() throws NoItemFoundException, Exception{
		return inventoryService.getInventoryList();
	}
	
	@PostMapping
	public String saveInventory(@RequestBody InventoryData inventoryData) throws NoItemFoundException, Exception{
		return inventoryService.save(new Inventory(inventoryData));
	}
	
	@PutMapping("/{id}")
	public String updateInventory(@PathVariable("id") long id, @RequestBody InventoryData inventoryData) throws NoItemFoundException, Exception{
		return inventoryService.update(inventoryData, id);
	}

}
