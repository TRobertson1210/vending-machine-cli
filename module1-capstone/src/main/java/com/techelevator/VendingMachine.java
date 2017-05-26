package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	public static BigDecimal currentBalance = new BigDecimal("0.00");
	public static BigDecimal previousBalance = new BigDecimal("0.00");
	public static List<Item> itemsInStock = new ArrayList<>();
	public Map<String, Item> itemMap = new HashMap<>();
	public BigDecimal billProvided;
	public boolean isOn = false;
	public static List<Item> purchasedItems = new ArrayList<Item>();
	public static Log log = new Log();


	@SuppressWarnings("resource")
	public void turnOn() {
		isOn = true;
		File inventory = new File("vendingmachine.csv");
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
		if(billProvided.compareTo(new BigDecimal("1.00")) == 0 || billProvided.compareTo(new BigDecimal("2.00")) == 0 || billProvided.compareTo(new BigDecimal("5.00")) == 0 || billProvided.compareTo(new BigDecimal("10.00")) == 0) {
			previousBalance = currentBalance;
			currentBalance = currentBalance.add(billProvided);
			log.printActionLog("FEED MONEY: ", previousBalance, currentBalance);
		} else {
			System.out.println("Please insert a valid bill. (1.00, 2.00, 5.00, 10.00)");
		}

	}

	public void getItem(String itemNumber) {
		if (!itemMap.containsKey(itemNumber)) {
			System.out.println("We don't stock that item.");
		} else if (itemMap.get(itemNumber).getQuantity() == 0) {
			System.out.println("That item is sold out!");
		} else if (currentBalance.compareTo(itemMap.get(itemNumber).getPrice()) == -1) {
			System.out.println("You don't have enough money for that!");
		} else {
			System.out.println("You have purchased: " + itemMap.get(itemNumber).getItemName());
			itemMap.get(itemNumber).vendItem();
			previousBalance = currentBalance;
			currentBalance = currentBalance.subtract(itemMap.get(itemNumber).getPrice());
			purchasedItems.add(itemMap.get(itemNumber));
			log.printActionLog(itemMap.get(itemNumber).getItemName() + " " + itemMap.get(itemNumber).getItemNumber(), previousBalance, currentBalance);
		}
	}

	public boolean isItOn() {
		return isOn;
	}


	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {  //Only used for testing.
		this.currentBalance = currentBalance;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public List<Item> getItemsInStock() {
		return itemsInStock;
	}

	public List<Item> getPurchasedItems() {
		return purchasedItems;
	}


	public Map<String, Item> getItemMap() {
		return itemMap;
	}

	public void setPreviousBalance(BigDecimal currentBalance) {
		previousBalance = currentBalance;
	}



}
