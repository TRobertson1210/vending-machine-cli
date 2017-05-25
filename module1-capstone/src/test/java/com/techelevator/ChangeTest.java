package com.techelevator;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChangeTest {
	
	Change sut;

	@Before
	public void setUp() throws Exception {
		sut = new Change();
	}

	@Test
	public void testGetChange() {
		BigDecimal currentBalance = new BigDecimal("2.00");
		Assert.assertArrayEquals(new int[] {8, 0, 0}, sut.getChange(currentBalance));
	}
	
	@Test
	public void testGetChangeDimes() {
		BigDecimal currentBalance = new BigDecimal("2.20");
		Assert.assertArrayEquals(new int[] {8, 2, 0}, sut.getChange(currentBalance));
	}
	
	@Test
	public void testGetMinimumAmtOfCoins() {
		BigDecimal currentBalance = new BigDecimal("2.35");
		Assert.assertArrayEquals(new int[] {9, 1, 0}, sut.getChange(currentBalance));
	}
	
	@Test
	public void testGetNickels() {
		BigDecimal currentBalance = new BigDecimal("2.05");
		Assert.assertArrayEquals(new int[] {8, 0, 1}, sut.getChange(currentBalance));
	}
	

}
