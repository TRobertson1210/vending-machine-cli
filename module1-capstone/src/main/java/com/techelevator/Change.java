package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Change {
	
	private BigDecimal quarter = new BigDecimal("0.25");
	private BigDecimal dime = new BigDecimal("0.10");
	private BigDecimal nickel = new BigDecimal("0.05");
	
	public int[] getChange(BigDecimal currentBalance) {
		int numberOfQuarters = 0;
		int numberOfDimes = 0;
		int numberOfNickels = 0;
		while (currentBalance.compareTo(BigDecimal.ZERO) > 0) {
			if (currentBalance.compareTo(quarter) >= 0) {
				currentBalance = currentBalance.subtract(quarter);
				numberOfQuarters++;
			} else if (currentBalance.compareTo(dime) >= 0) {
				currentBalance = currentBalance.subtract(dime);
				numberOfDimes++;
			} else {
				currentBalance = currentBalance.subtract(nickel);
				numberOfNickels++;
			}
		}
		
		int[] numberOfCoins = new int[] {numberOfQuarters, numberOfDimes, numberOfNickels};
		
		return numberOfCoins;
	}
	
	

}
