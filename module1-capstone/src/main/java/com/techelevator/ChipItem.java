package com.techelevator;

import java.math.BigDecimal;

public class ChipItem extends Item {
	
	public ChipItem(String itemNumber, String itemName, BigDecimal price) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.price = price;
	}

	@Override
	public String consumeItem() {
		return "Crunch Crunch, Yum!";
	}

}
