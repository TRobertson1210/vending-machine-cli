package com.techelevator;

import java.math.BigDecimal;

public class DrinkItem extends Item {
	
	public DrinkItem(String itemNumber, String itemName, BigDecimal price) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.price = price;
	}

	@Override
	public String consumeItem() {
		return "Glug Glug, Yum!";
	}

}
