package com.haaris.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  private Item item; // Item object to use throughout tests.

  @BeforeEach
  void beforeEach() {
    item = new Item("Twix", "130", 19);
  }

  // Test 1.
  // Testing return of name.
  @Test
  void testGetName() {
    assertEquals("Twix", item.getName());
  }

  // Test 2.
  // Testing return of price.
  @Test
  void testGetPrice() {
    assertEquals(new BigDecimal("130.00"), item.getPrice());
  }

  // Test 3.
  // Testing return of quantity.
  @Test
  void testGetQuantity() {
    assertEquals(19, item.getAmount());
  }

  // Test 4.
  // Testing setting new name.
  @Test
  void testSetName() {
    assertEquals("Twix", item.getName());

    item.setName("Mars");

    assertEquals("Mars", item.getName());
  }

  // Test 5.
  // Testing setting new price.
  @Test
  void testSetPrice() {
    assertEquals(new BigDecimal("130.00"), item.getPrice());

    item.setPriceString("100");

    assertEquals(new BigDecimal("100.00"), item.getPrice());
  }

  // Test 6.
  // Testing setting new quantity.
  @Test
  void testSetQuantity() {
    assertEquals(19, item.getAmount());

    item.setAmount(10);

    assertEquals(10, item.getAmount());
  }

  // Test 7.
  // Testing setting negative quantity.
  @Test
  void testSetNegativeQuantity() {
    assertEquals(19, item.getAmount());

    assertThrows(IllegalArgumentException.class, () -> item.setAmount(-1));

    assertEquals(19, item.getAmount());
  }

  // Test 8.
  // Testing setting new price with Big Decimal.
  @Test
  void testSetPriceBD() {
    assertEquals(new BigDecimal("130.00"), item.getPrice());

    item.setPriceBD(new BigDecimal("90").setScale(5, RoundingMode.HALF_UP));

    assertEquals(new BigDecimal("90.00"), item.getPrice()); // Note that rounding is 2.
  }
}