package com.techelevator;

import java.math.BigDecimal;

public class GumItem extends Item {
	
	public GumItem(String itemNumber, String itemName, BigDecimal price) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.price = price;
	}

	@Override
	public String consumeItem() {
		return "Chew Chew, Yum!";
	}

}
