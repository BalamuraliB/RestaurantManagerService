package com.swatkats.restaurantManager.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swatkats.restaurantManager.DAO.MenuItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MenuItemData {
	
	private long id;
	private String name;
	private double price;
	private String type;
	private boolean available;
	private List<InventoryMenuItemData> inventoryList;	

	public MenuItemData(MenuItem menuItem) {
		super();
		this.setId(menuItem.getId());
		this.setAvailable(menuItem.isAvailable());
		this.setName(menuItem.getName());
		this.setPrice(menuItem.getPrice());
		this.setType(menuItem.getType());
	}
}
