package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingMachineCLI extends VendingMachine {


	public void displayMainMenu() {
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.print("What would you like to do? ");

	}

	public static void getItemsInStock() {	
		System.out.print("\n");
		for(Item item : itemsInStock) {
			String format = "%-18s";
			System.out.print(item.getItemNumber());
			System.out.format(format, "\t" + item.getItemName());
			System.out.print("\t" + item.getPrice());
			if(item.getQuantity() == 0) {
				System.out.format("%-10s", "\tSOLD OUT\n");
			} else {
				System.out.format(format, "\tQuantity remaining: " + item.getQuantity() + "\n");
			}
		}
		System.out.print("\n");
	}

	public void purchase() {
		System.out.println("\n(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println("Current Money Provided: $" + VendingMachine.getCurrentBalance());
		System.out.print("\nWhat would you like to do? ");
	}

	public void askForMoney() {
		System.out.print("How much money are you putting in? (X.XX) ");
	}

	public void askForProduct() {
		System.out.print("Which item would you like to buy? ");
	}

	public void finishTransaction() {
		Change transaction = new Change();
		int[] numberOfCoins = transaction.getChange(currentBalance);
		
		System.out.print("Your change is: ");
		if (numberOfCoins.length == 0) {
			System.out.println("$0.00.");
		} 
		
		if (numberOfCoins[0] > 0) {
			System.out.print(numberOfCoins[0] + "Q ");
		} 
		
		if (numberOfCoins[1] > 0) {
			System.out.print(numberOfCoins[1] + "D ");
		} 
		
		if (numberOfCoins[2] > 0) {
			System.out.print(numberOfCoins[2] + "N ");
		} 
		
		System.out.println("\n");
		previousBalance = currentBalance;
		currentBalance = new BigDecimal("0.00");
		log.printActionLog("GIVE CHANGE: ", previousBalance, currentBalance);
		for(Item element : purchasedItems) {
			System.out.println(element.consumeItem());
		}
		System.out.println();
		purchasedItems.clear();
		
		
	}

}
