package com.swatkats.restaurantManager.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderMenuData {
	
	private long id;
	private long menuId;
	private String menuName;
	private int quantity;

	
}
