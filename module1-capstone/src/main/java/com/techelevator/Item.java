package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {
	
	protected String itemName;
	protected String itemNumber;
	protected BigDecimal price;
	protected int quantity = 5;
	
	
	public void vendItem() {
		quantity--;
	}
	

	public BigDecimal getPrice() {
		return price;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public String consumeItem() {
		return null;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {  //Only used for testing.
		this.quantity = quantity;
	}

}
