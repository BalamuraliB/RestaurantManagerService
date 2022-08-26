package com.swatkats.restaurantManager.DAO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.swatkats.restaurantManager.DTO.MenuItemData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MenuItem")
@Getter
@Setter
@NoArgsConstructor
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "available")
	private boolean available;
	@Column(name = "price")
	private double price;
	@Column(name = "type")
	private String type;
	@OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
	private List<InventoryMenuItem> inventoryList;
	
	public MenuItem(MenuItemData request, List<InventoryMenuItem> inventoryList) {
		super();
		this.setAvailable(request.isAvailable());
		this.setName(request.getName());
		this.setPrice(request.getPrice());
		this.setType(request.getType());
		this.setInventoryList(inventoryList);
	}
	
	public MenuItem(MenuItemData request) {
		super();
		this.setAvailable(request.isAvailable());
		this.setName(request.getName());
		this.setPrice(request.getPrice());
		this.setType(request.getType());
	}
}
