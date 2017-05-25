package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		VendingMachine machine = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI();

		machine.turnOn();

		while (machine.isItOn()) {

			cli.displayMainMenu();

			Scanner input = new Scanner(System.in);
			int mainMenuChoice = input.nextInt();
			input.nextLine();
			int purchaseMenuChoice = 0;

			while (mainMenuChoice == 1) {
				cli.getItemsInStock();
				cli.displayMainMenu();
				mainMenuChoice = input.nextInt();
				input.nextLine();
			} 

			if (mainMenuChoice == 2) {
				cli.purchase();
				purchaseMenuChoice = input.nextInt();
				input.nextLine();
			}


			while (purchaseMenuChoice == 1 || purchaseMenuChoice == 2) {
				if (purchaseMenuChoice == 1) {
					cli.askForMoney();
					BigDecimal billProvided = input.nextBigDecimal();
					input.nextLine();
					machine.feedMoney(billProvided);
					cli.purchase();
					purchaseMenuChoice = input.nextInt();
					input.nextLine();
				} else if (purchaseMenuChoice == 2) {
					cli.askForProduct();
					String itemNumber = input.nextLine();
					machine.getItem(itemNumber);
					cli.purchase();
					purchaseMenuChoice = input.nextInt();
					input.nextLine();
				}
			}

			if (purchaseMenuChoice == 3) {
				cli.finishTransaction();
			}


		}

	}

}
