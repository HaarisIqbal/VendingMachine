package com.service;

import com.dao.AuditDAO;
import com.dao.AuditDAOFileImpl;
import com.dao.VendingMachineDAO;
import com.dao.VendingMachineDAOFileImpl;
import com.dto.Coins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest {
  // Test service layer to use throughout tests.
  VendingMachineDAO vmDAO = new VendingMachineDAOFileImpl();
  AuditDAO auditDAO = new AuditDAOFileImpl();
  VendingMachineServiceLayer testServiceLayer = new VendingMachineServiceLayerImpl(vmDAO, auditDAO);

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