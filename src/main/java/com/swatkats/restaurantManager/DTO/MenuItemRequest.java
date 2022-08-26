package com.swatkats.restaurantManager.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MenuItemRequest {
	
	private long id;
	private String name;
	private double price;
	private String type;
	private boolean available;
	private List<InventoryMenuItemData> inventoryList;	

}
