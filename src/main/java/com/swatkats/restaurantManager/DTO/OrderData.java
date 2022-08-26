package com.swatkats.restaurantManager.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swatkats.restaurantManager.DAO.FoodOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderData {
	
	private long id;
	private int tableNo;
	private String status;
	private List<OrderMenuData> menuList;

	public OrderData(FoodOrder foodOrder) {
		super();
		this.setId(foodOrder.getId());
		this.setStatus(foodOrder.getStatus());
		this.setTableNo(foodOrder.getTableNo());
	}
}
