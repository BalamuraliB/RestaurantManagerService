package com.swatkats.restaurantManager.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "InventoryMenuItem")
@Getter
@Setter
public class InventoryMenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@ManyToOne
	@JoinColumn(name = "menuItemId", nullable = false)
	private MenuItem menuItem;
	@ManyToOne
	@JoinColumn(name = "inventoryId", nullable = false)
	private Inventory inventory;
	@Column(name="units")
	private double units;
	
}
