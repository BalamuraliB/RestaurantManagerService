package com.swatkats.restaurantManager.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.swatkats.restaurantManager.DTO.InventoryData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Inventory")
@Getter
@Setter
@NoArgsConstructor
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "unitsAvailable")
	private double unitsAvailable;
	
	public Inventory(InventoryData inventoryData) {
		super();
		this.setId(inventoryData.getId());
		this.setName(inventoryData.getName());
		this.setUnitsAvailable(inventoryData.getUnitsAvailable());
	}

}
