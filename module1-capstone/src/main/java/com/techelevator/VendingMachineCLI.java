package com.techelevator;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	public static void displayMainMenu() {
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.print("What would you like to do? ");

	}

	public static void getItemsInStock(List<Item> itemsInStock) {
		System.out.print("\n");
		for (Item item : itemsInStock) {
			String format = "%-18s";
			System.out.print(item.getItemNumber());
			System.out.format(format, "\t" + item.getItemName());
			System.out.print("\t" + item.getPrice());
			if (item.getQuantity() == 0) {
				System.out.format("%-10s", "\tSOLD OUT\n");
			} else {
				System.out.format(format, "\tQuantity remaining: " + item.getQuantity() + "\n");
			}
		}
		System.out.print("\n");
	}

	public static void displayPurchaseMenu(BigDecimal currentBalance) {
		System.out.println("\n(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println("Current Money Provided: $" + currentBalance);
		System.out.print("\nWhat would you like to do? ");
	}

	public static void askForMoney() {
		System.out.print("How much money are you putting in? (X.XX) ");
	}

	public static void askForProduct() {
		System.out.print("Which item would you like to buy? ");
	}

	public static void finishTransaction(List<Item> purchasedItems, BigDecimal currentBalance,
			BigDecimal previousBalance) {
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

		for (Item element : purchasedItems) {
			System.out.println(element.consumeItem());
		}
		System.out.println();
		purchasedItems.clear();
	}

	public static void main(String[] args) {

		VendingMachine machine = new VendingMachine();

		machine.turnOn();

		while (machine.isItOn()) {
			try {
				displayMainMenu();

				Scanner input = new Scanner(System.in);
				int mainMenuChoice = input.nextInt();
				input.nextLine();
				int purchaseMenuChoice = 0;

				if (mainMenuChoice == 1) {
					getItemsInStock(machine.getItemsInStock());
				} else if (mainMenuChoice == 2) {
					displayPurchaseMenu(machine.getCurrentBalance());
					purchaseMenuChoice = input.nextInt();
					input.nextLine();
					
					while (purchaseMenuChoice == 1 || purchaseMenuChoice == 2) {
						if (purchaseMenuChoice == 1) {
							askForMoney();
							BigDecimal billProvided = input.nextBigDecimal();
							input.nextLine();
							machine.feedMoney(billProvided);
							displayPurchaseMenu(machine.getCurrentBalance());
							purchaseMenuChoice = input.nextInt();
							input.nextLine();
						} else if (purchaseMenuChoice == 2) {
							askForProduct();
							String itemNumber = input.nextLine();
							try {
								machine.getItem(itemNumber);
							} catch (ItemNotFoundException e) {
								System.out.println(e.getMessage());
							}
							displayPurchaseMenu(machine.getCurrentBalance());
							purchaseMenuChoice = input.nextInt();
							input.nextLine();
						}
					}

					if (purchaseMenuChoice == 3) {
						finishTransaction(machine.getPurchasedItems(), machine.getCurrentBalance(),
								machine.getPreviousBalance());
						machine.setPreviousBalance(machine.getCurrentBalance());
						machine.setCurrentBalance(new BigDecimal("0.00"));
						Log log = new Log();
						log.printActionLog("GIVE CHANGE: ", machine.getPreviousBalance(), machine.getCurrentBalance());
					}

				}


			} catch (InputMismatchException e) {
				System.out.println("Please use a valid input.\n");
			}

		}
	}
}
