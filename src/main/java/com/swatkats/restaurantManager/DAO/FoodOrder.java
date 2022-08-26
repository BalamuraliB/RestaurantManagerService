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

import com.swatkats.restaurantManager.DTO.OrderData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FoodOrder")
@Getter
@Setter
@NoArgsConstructor
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
	
	public FoodOrder(OrderData request) {
		super();
		this.setStatus(request.getStatus());
		this.setTableNo(request.getTableNo());
	}
}