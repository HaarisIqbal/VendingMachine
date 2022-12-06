/**
 * Wiley Edge Project 3, November 2022.
 * Item class, to represent Items (data transfer object).
 *
 * @author Haaris Iqbal
 */

package com.haaris.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
  // Key Attributes of Item.
  private String name;
  private int amount;
  private BigDecimal price;

  /**
   * Constructor method: Instantiates a new Item.
   *
   * @param name   the name of the item.
   * @param price  the price of the item.
   * @param amount the quantity of the item.
   */
  public Item(String name, String price, int amount) {
    this.name = name;
    this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
    setAmount(amount);
  }

  // Getter methods.

  /**
   * Gets name.
   *
   * @return the name of the item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets price.
   *
   * @return the price of the item.
   */
  public BigDecimal getPrice() {
    return this.price;
  }

  /**
   * Gets amount.
   *
   * @return the quantity of the item.
   */
  public int getAmount() {
    return this.amount;
  }

  // Setter methods.

  /**
   * Sets name.
   *
   * @param name the new name of the item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets price with String.
   *
   * @param price the new price of the item.
   */
  public void setPriceString(String price) {
    this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);;
  }

  /**
   * Sets price with Big Decimal.
   *
   * @param price the new price of the item.
   */
  public void setPriceBD(BigDecimal price) {
    this.price = price.setScale(2, RoundingMode.HALF_UP);;
  }

  /**
   * Sets amount.
   *
   * @param amount the new quantity of the item.
   */
  public void setAmount(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }

    this.amount = amount;
  }
}
