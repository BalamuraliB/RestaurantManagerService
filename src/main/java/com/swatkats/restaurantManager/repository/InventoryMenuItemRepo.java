package com.swatkats.restaurantManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatkats.restaurantManager.DAO.InventoryMenuItem;

@Repository
public interface InventoryMenuItemRepo extends JpaRepository<InventoryMenuItem, Long>{
	
}
