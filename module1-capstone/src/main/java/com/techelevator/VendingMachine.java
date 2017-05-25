package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	protected static BigDecimal currentBalance = new BigDecimal("0.00");
	protected BigDecimal previousBalance = new BigDecimal("0.00");
	public static List<Item> itemsInStock = new ArrayList<>();
	public Map<String, Item> itemMap = new HashMap<>();
	protected BigDecimal billProvided;
	protected boolean isOn = false;


	@SuppressWarnings("resource")
	public void turnOn() {
		isOn = true;
		File inventory = new File("/Users/andrewgutierrez/workspace/team4-java-module1-capstone/module1-capstone/vendingmachine.csv");
		try {
			Scanner fileScanner = new Scanner(inventory);
			while (fileScanner.hasNextLine()) {					// Stocks the machine
				String line = fileScanner.nextLine();
				String[] itemInfo = line.split("\\|");
				BigDecimal price = new BigDecimal(itemInfo[2]);
				if(itemInfo[0].contains("A")) {
					ChipItem chip = new ChipItem(itemInfo[0], itemInfo[1], price);
					itemsInStock.add(chip);
					itemMap.put(itemInfo[0], chip);
				} else if (itemInfo[0].contains("B")) {
					CandyItem candy = new CandyItem(itemInfo[0], itemInfo[1], price);
					itemsInStock.add(candy);
					itemMap.put(itemInfo[0], candy);
				} else if (itemInfo[0].contains("C")) {
					DrinkItem drink = new DrinkItem(itemInfo[0], itemInfo[1], price);
					itemsInStock.add(drink);
					itemMap.put(itemInfo[0], drink);
				} else if (itemInfo[0].contains("D")) {
					GumItem gum = new GumItem(itemInfo[0], itemInfo[1], price);
					itemsInStock.add(gum);
					itemMap.put(itemInfo[0], gum);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.exit(1);
		}
	}

	
	public void feedMoney(BigDecimal billProvided) {
		currentBalance = currentBalance.add(billProvided);
	}
	
	public void getItem(String itemNumber) {
		if (!itemMap.containsKey(itemNumber)) {
			System.out.println("We don't stock that item.");
		} else if (itemMap.get(itemNumber).getQuantity() == 0) {
			System.out.println("That item is sold out!");
		} else if (currentBalance.compareTo(itemMap.get(itemNumber).getPrice()) == -1) {
			System.out.println("You don't have enough money for that!");
		} else {
			itemMap.get(itemNumber).vendItem();
			currentBalance = currentBalance.subtract(itemMap.get(itemNumber).getPrice());
		}
	}
	
	public void TurnOff() {
		isOn = false;
	}
	
	public boolean isItOn() {
		return isOn;
	}

	public static BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}
	
	
	



}
