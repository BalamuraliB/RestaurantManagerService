package com.swatkats.restaurantManager.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swatkats.restaurantManager.DAO.Inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class InventoryData {

	private long id;
	private String name;
	private double unitsAvailable;
	
	public InventoryData(Inventory inventory) {
		super();
		this.setId(inventory.getId());
		this.setName(inventory.getName());
		this.setUnitsAvailable(inventory.getUnitsAvailable());
	}
	
}
