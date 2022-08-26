package com.swatkats.restaurantManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatkats.restaurantManager.DAO.OrderMenu;

@Repository
public interface OrderMenuRepo extends JpaRepository<OrderMenu, Long> {

}
