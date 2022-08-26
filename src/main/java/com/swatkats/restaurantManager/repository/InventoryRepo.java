package com.swatkats.restaurantManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatkats.restaurantManager.DAO.Inventory;


@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long>{

}
