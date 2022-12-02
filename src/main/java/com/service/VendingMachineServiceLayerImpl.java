/**
 * Wiley Edge Project 3, November 2022.
 * ServiceLayer class, to manipulate DAOs.
 *
 * @author Haaris Iqbal
 */

package com.service;

import com.dao.AuditDAO;
import com.dao.VendingMachineDAO;
import com.dto.Change;
import com.dto.Coins;
import com.dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
  private VendingMachineDAO vmDAO;
  private AuditDAO auditDAO;

  /**
   * Instantiates a new Vending machine service layer.
   *
   * @param vmDAO    the vm dao to be injected.
   * @param auditDAO the audit dao to be injected.
   */
  public VendingMachineServiceLayerImpl(VendingMachineDAO vmDAO, AuditDAO auditDAO) {
    this.vmDAO = vmDAO;
    this.auditDAO = auditDAO;
  }

  /**
   * Instantiating DAO files if they don't already exist.
   */
  @Override
  public void initializeFiles() {
    auditDAO.createAuditFile();
    vmDAO.createItemFile();
  }

  /**
   * To determine available items from full list of items.
   *
   * @return a HashMap of available items, and their prices.
   */
  @Override
  public HashMap<String, BigDecimal> getAvailableItems() {
    HashMap<String, BigDecimal> availableItems = new HashMap<String, BigDecimal>();

    // Using lambda functions to construct HashMap of items with quantity above zero.
    vmDAO.getItems().stream()
            .filter((item) -> item.getAmount() > 0)
            .forEach((item) -> {
              availableItems.put(item.getName(), item.getPrice());
            });

    return availableItems;
  }

  /**
   * Determine quantity of item.
   *
   * @param itemName the name of the item whose quantity is requested.
   * @return the quantity of the item.
   */
  @Override
  public int getItemAmount(String itemName) {
    int amount = 0;

    for (Item item : vmDAO.getItems()) {
      if (item.getName().equals(itemName)) {
        amount = item.getAmount();
      }
    }

    return amount;
  }

  /**
   * To get change in coins.
   *
   * @param money the money to get change for.
   * @return a HashMap of quarters, dimes, nickels and pennies.
   */
  @Override
  public HashMap<Coins, Integer> getChange(BigDecimal money) {
    Change c = new Change(money);

    return c.getChange();
  }

  /**
   * To update file and log purchase event.
   *
   * @param itemName the Item that has been purchased.
   */
  @Override
  public void updateItemFile(String itemName) {
    vmDAO.takeOne(itemName);
    auditDAO.writeAuditEntry(
            "'" + itemName + "' PURCHASED LEAVING " + getItemAmount(itemName) + " REMAINING IN ITEM FILE.");
  }
}
