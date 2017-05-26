package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine sut;
	
	@Before
	public void setUp() throws Exception {
		sut = new VendingMachine();
		sut.turnOn();
	}

	@Test
	public void testTurnOn() {
		assertEquals("Potato Crisps", sut.getItemMap().get("A1").getItemName());
		assertEquals(new BigDecimal("3.05"), sut.getItemMap().get("A1").getPrice());
	}

	@Test
	public void testFeedMoney3Dollars() {
		sut.feedMoney(new BigDecimal("3.00"));
		assertEquals(new BigDecimal("0.00"), sut.getCurrentBalance()); //This should fail, meaning the balance doesn't update and the console prints an error message	
	}
	
	@Test
	public void testFeedMoney5Dollars() {
		sut.feedMoney(new BigDecimal("5.00"));
		assertEquals(new BigDecimal("5.00"), sut.getCurrentBalance()); //This should work
		assertEquals(new BigDecimal("0.00"), sut.getPreviousBalance());
	}

	@Test
	public void testGetWrongItem() {
		sut.getItem("F7"); //this value should fail
		sut.setCurrentBalance(new BigDecimal("5.00"));
		assertEquals(new BigDecimal("5.00"), sut.getCurrentBalance());
	}
	
	@Test
	public void testSoldOutItem() {
		sut.getItemMap().get("A1").setQuantity(0);
		sut.setCurrentBalance(new BigDecimal("5.00"));
		assertEquals(new BigDecimal("5.00"), sut.getCurrentBalance()); //This will fail because it can't buy a sold out item.
	}
	
	@Test
	public void testGetItem() {
		sut.setCurrentBalance(new BigDecimal("5.00"));
		sut.getItem("A1");
		assertEquals(new BigDecimal("1.95"), sut.getCurrentBalance());
		assertTrue(sut.getPurchasedItems().contains(sut.getItemMap().get("A1")));
	}

	@After
	public void tearDown() {
		sut.setCurrentBalance(new BigDecimal("0.00"));
	}
}
