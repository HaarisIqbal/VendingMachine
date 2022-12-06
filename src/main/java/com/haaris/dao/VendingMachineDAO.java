package com.haaris.dao;

import com.haaris.dto.Item;

import java.util.HashSet;

public interface VendingMachineDAO {
  void createItemFile();

  HashSet<Item> getItems();

  void takeOne(String itemName);
}
