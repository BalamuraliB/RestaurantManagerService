package com.swatkats.restaurantManager.DAO;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FoodOrder")
@Getter
@Setter
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "tableNo")	
	private int tableNo;
	@Column(name = "status")
	private String status;
	@Column(name = "orderedAt")
	private Time orderedAt;
	@Column(name = "updatedAt")
	private Time updatedAt;
	@OneToMany(mappedBy="order")
	private List<OrderMenu> menu;
	
	
}