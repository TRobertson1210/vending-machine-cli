package com.techelevator;

import java.math.BigDecimal;

public class CandyItem extends Item {
	
	public CandyItem(String itemNumber, String itemName, BigDecimal price) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.price = price;
	}

	@Override
	public String consumeItem() {
		return "Munch Munch, Yum!";
	}

}
