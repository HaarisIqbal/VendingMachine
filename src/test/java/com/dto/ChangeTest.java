package com.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTest {

  private Change change;
  private HashMap<Coins, Integer> outcome = new HashMap<Coins, Integer>();

  @BeforeEach
  void beforeEach() {
    // fresh outcome HashMap before each test.
    outcome.put(Coins.QUARTER, 0);
    outcome.put(Coins.DIME, 0);
    outcome.put(Coins.NICKEL, 0);
    outcome.put(Coins.PENNY, 0);
  }

  // Test 1.
  // Testing simple case of pennies.
  @Test
  void testPennyChange() {
    // 1P.
    outcome.put(Coins.PENNY, 1);
    change = new Change(new BigDecimal("1"));

    assertEquals(outcome, change.getChange());

    // 4P.
    outcome.put(Coins.PENNY, 4);
    change = new Change(new BigDecimal("4"));

    assertEquals(outcome, change.getChange());
  }

  // Test 2.
  // Testing case of pennies and nickels.
  @Test
  void testNickelChange() {
    // 1N.
    outcome.put(Coins.NICKEL, 1);
    change = new Change(new BigDecimal("5"));

    assertEquals(outcome, change.getChange());

    // 1N, 2P.
    outcome.put(Coins.NICKEL, 1);
    outcome.put(Coins.PENNY, 2);
    change = new Change(new BigDecimal("7"));

    assertEquals(outcome, change.getChange());

    // 1N, 4P.
    outcome.put(Coins.NICKEL, 1);
    outcome.put(Coins.PENNY, 4);
    change = new Change(new BigDecimal("9"));

    assertEquals(outcome, change.getChange());
  }

  // Test 3.
  // Testing case of pennies, nickels and dimes.
  @Test
  void testDimeChange() {
    // 1D.
    outcome.put(Coins.DIME, 1);
    change = new Change(new BigDecimal("10"));

    assertEquals(outcome, change.getChange());

    // 1D, 2P.
    outcome.put(Coins.DIME, 1);
    outcome.put(Coins.PENNY, 2);
    change = new Change(new BigDecimal("12"));

    assertEquals(outcome, change.getChange());

    // 1D, 1N, 4P.
    outcome.put(Coins.DIME, 1);
    outcome.put(Coins.NICKEL, 1);
    outcome.put(Coins.PENNY, 4);
    change = new Change(new BigDecimal("19"));

    assertEquals(outcome, change.getChange());
  }

  // Test 4.
  // Testing case of pennies, nickels, dimes and quarters.
  @Test
  void testQuarterChange() {
    // 1Q.
    outcome.put(Coins.QUARTER, 1);
    change = new Change(new BigDecimal("25"));

    assertEquals(outcome, change.getChange());

    // 1Q, 4P.
    outcome.put(Coins.QUARTER, 1);
    outcome.put(Coins.PENNY, 4);
    change = new Change(new BigDecimal("29"));

    assertEquals(outcome, change.getChange());

    // 1Q, 1N, 3P.
    outcome.put(Coins.QUARTER, 1);
    outcome.put(Coins.NICKEL, 1);
    outcome.put(Coins.PENNY, 3);
    change = new Change(new BigDecimal("33"));

    assertEquals(outcome, change.getChange());

    // 1Q, 2D
    outcome.put(Coins.QUARTER, 1);
    outcome.put(Coins.DIME, 2);
    outcome.put(Coins.NICKEL, 0);
    outcome.put(Coins.PENNY, 0);
    change = new Change(new BigDecimal("45"));

    assertEquals(outcome, change.getChange());
  }

  // Test 5.
  // Testing large case.
  @Test
  void testLargeReturn() {
    // 49Q, 2D, 0N, 4P.
    outcome.put(Coins.QUARTER, 49);
    outcome.put(Coins.DIME, 2);
    outcome.put(Coins.NICKEL, 0);
    outcome.put(Coins.PENNY, 4);
    change = new Change(new BigDecimal("1249"));

    assertEquals(outcome, change.getChange());

    // 49377Q.
    outcome.put(Coins.QUARTER, 49377);
    outcome.put(Coins.DIME, 0);
    outcome.put(Coins.NICKEL, 0);
    outcome.put(Coins.PENNY, 0);
    change = new Change(new BigDecimal("1234425"));

    assertEquals(outcome, change.getChange());
  }

  // Should unit test getter methods.

}