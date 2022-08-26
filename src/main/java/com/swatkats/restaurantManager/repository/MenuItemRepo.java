package com.swatkats.restaurantManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatkats.restaurantManager.DAO.MenuItem;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

}
