package com.haaris.service;

import com.haaris.dto.Coins;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest {
  // Test service layer to use throughout tests.
  ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
  VendingMachineServiceLayer testServiceLayer = ctx.getBean("serviceLayer", VendingMachineServiceLayerImpl.class);

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
  // Testing return of available items.
  @Test
  void testGetAvailableItems() {
    assertEquals(6, testServiceLayer.getAvailableItems().size());
  }

  // Test 2.
  // Testing change functionality.
  @Test
  void testGetChange() {
    outcome.put(Coins.QUARTER, 5);
    outcome.put(Coins.DIME, 2);
    outcome.put(Coins.NICKEL, 0);
    outcome.put(Coins.PENNY, 4);

    assertEquals(outcome, testServiceLayer.getChange(new BigDecimal("149")));
  }
}