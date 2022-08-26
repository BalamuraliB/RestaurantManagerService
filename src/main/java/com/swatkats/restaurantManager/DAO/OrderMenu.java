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
@Table(name = "OrderMenu")
@Getter
@Setter
public class OrderMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@ManyToOne
	@JoinColumn(name="orderId", nullable=false)
	private FoodOrder order;
	@ManyToOne
	@JoinColumn(name="menuItemId", nullable=false)
	private MenuItem menuItem;
	@Column(name = "quantity")
	private int quantity;

}
