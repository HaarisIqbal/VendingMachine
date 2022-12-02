package com.dao;

import com.dto.Item;

import java.util.HashMap;
import java.util.HashSet;

public interface VendingMachineDAO {
  void createItemFile();

  HashSet<Item> getItems();

  void takeOne(String itemName);
}
