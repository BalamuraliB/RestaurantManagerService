package com.swatkats.restaurantManager.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatkats.restaurantManager.DAO.FoodOrder;
import com.swatkats.restaurantManager.DAO.MenuItem;
import com.swatkats.restaurantManager.DAO.OrderMenu;
import com.swatkats.restaurantManager.DTO.OrderData;
import com.swatkats.restaurantManager.DTO.OrderMenuData;
import com.swatkats.restaurantManager.exception.InvalidRequestException;
import com.swatkats.restaurantManager.exception.NoItemFoundException;
import com.swatkats.restaurantManager.repository.MenuItemRepo;
import com.swatkats.restaurantManager.repository.OrderMenuRepo;
import com.swatkats.restaurantManager.repository.OrderRepo;
import com.swatkats.restaurantManager.utils.RestaurantManagerConstants;
import com.swatkats.restaurantManager.utils.ValidateUtils;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderMenuRepo orderMenuRepo;
	
	@Autowired
	MenuItemRepo menuItemRepo;

	public OrderData getOrderById(long id) throws InvalidRequestException {
		ValidateUtils.validateId(id);
		Optional<FoodOrder> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			throw new InvalidRequestException("No Menu with id: "+id+" found");
		}
		OrderData response = translateOrder(order.get());
		return response;
	}

	public List<OrderData> getOrderList() throws NoItemFoundException {
		List<FoodOrder> orderList = orderRepo.findAll();
		if(orderList.size() == 0) {
			throw new NoItemFoundException("No Menu Items are available");
		}
		List<OrderData> responseList = new ArrayList<OrderData>();
		for(FoodOrder foodOrder : orderList) {
			OrderData orderData = new OrderData(foodOrder);
			responseList.add(orderData);
		}
		return responseList;
	}

	public String save(OrderData request) throws InvalidRequestException {
		ValidateUtils.validateOrderRequest(request);
		FoodOrder foodOrder = new FoodOrder(request);
		foodOrder.setOrderedAt(new Time(new Date().getTime()));
		processMenuList(request.getMenuList(), foodOrder);
		orderRepo.save(foodOrder);
		orderMenuRepo.saveAll(foodOrder.getMenu());
		return RestaurantManagerConstants.SAVE_DATA_SUCCESS;
	}

	public String update(long id, OrderData request) throws InvalidRequestException {
		ValidateUtils.validateId(id);
		ValidateUtils.validateOrderRequest(request);
		Optional<FoodOrder> foodOrder = orderRepo.findById(id);
		if(foodOrder.isEmpty()) {
			throw new InvalidRequestException("No Order with id: "+id+" found");
		}
		foodOrder.get().setOrderedAt(new Time(new Date().getTime()));
		foodOrder.get().setStatus(request.getStatus());
		processMenuList(request.getMenuList(), foodOrder.get());
		orderRepo.save(foodOrder.get());
		orderMenuRepo.saveAll(foodOrder.get().getMenu());
		return RestaurantManagerConstants.UPDATE_DATA_SUCCESS;
	}

	private void processMenuList(List<OrderMenuData> menuList, FoodOrder order) throws InvalidRequestException {
		List<OrderMenu> orderList = new ArrayList<OrderMenu>();
		for(OrderMenuData menuData : menuList) {
			if(menuData.getId() != 0) {
				Optional<OrderMenu> orderMenuData = orderMenuRepo.findById(menuData.getId());
				if(orderMenuData.isEmpty()) {
					throw new InvalidRequestException("No Order menu correlation with id: "+menuData.getId()+" found");
				}
				orderMenuData.get().setQuantity(menuData.getQuantity());
				orderList.add(orderMenuData.get());
			} else {
				Optional<MenuItem> menuItem = menuItemRepo.findById(menuData.getMenuId());
				if(menuItem.isEmpty()) {
					throw new InvalidRequestException("No menu with id: "+menuData.getMenuId()+" found");
				}
				OrderMenu orderMenu = new OrderMenu();
				orderMenu.setMenuItem(menuItem.get());
				orderMenu.setOrder(order);
				orderMenu.setQuantity(menuData.getQuantity());
				orderList.add(orderMenu);
			}	
		}
		if(orderList.size() == 0) {
			throw new InvalidRequestException("No Valid Inventories found for menu request");
		}
		order.setMenu(orderList);	
	}
	
	private OrderData translateOrder(FoodOrder foodOrder) {
		OrderData orderData = new OrderData(foodOrder);
		List<OrderMenuData> orderMenuList = new ArrayList<OrderMenuData>();
		for(OrderMenu menuData : foodOrder.getMenu()) {
			OrderMenuData menuResponse = new OrderMenuData();
			menuResponse.setId(menuData.getId());
			menuResponse.setMenuId(menuData.getMenuItem().getId());
			menuResponse.setMenuName(menuData.getMenuItem().getName());
			menuResponse.setQuantity(menuData.getQuantity());
			orderMenuList.add(menuResponse);
		}
		orderData.setMenuList(orderMenuList);
		return orderData;
	}
}
