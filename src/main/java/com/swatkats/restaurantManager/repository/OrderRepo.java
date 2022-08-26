package com.swatkats.restaurantManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatkats.restaurantManager.DAO.FoodOrder;

@Repository
public interface OrderRepo extends JpaRepository<FoodOrder, Long>{

}
