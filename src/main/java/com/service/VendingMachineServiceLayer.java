package com.service;

import com.dto.Coins;

import java.math.BigDecimal;
import java.util.HashMap;

public interface VendingMachineServiceLayer {
  void initializeFiles();

  HashMap<String, BigDecimal> getAvailableItems();

  int getItemAmount(String itemName);

  HashMap<Coins, Integer> getChange(BigDecimal money);

  void updateItemFile(String itemName);
}
