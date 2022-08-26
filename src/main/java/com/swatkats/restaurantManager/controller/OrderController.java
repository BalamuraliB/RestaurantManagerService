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

import com.swatkats.restaurantManager.DTO.OrderData;
import com.swatkats.restaurantManager.exception.InvalidRequestException;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/{id}")
	public OrderData getOrderData(@PathVariable long id) throws InvalidRequestException {
		return orderService.getOrderById(id);
	}
	
	@GetMapping
	public List<OrderData> getOrderList() throws NoItemFoundException {
		return orderService.getOrderList();
	}
	
	@PostMapping
	public String saveOrderData(@RequestBody OrderData foodOrder) throws InvalidRequestException {
		return orderService.save(foodOrder);
	}
	
	@PutMapping("/{id}")
	public String updateOrderData(@PathVariable long id, @RequestBody OrderData foodOrder) throws InvalidRequestException {
		return orderService.update(id, foodOrder);
	}

}
