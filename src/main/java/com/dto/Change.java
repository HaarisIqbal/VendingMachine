/**
 * Wiley Edge Project 3, November 2022.
 * Change class, to calculate change in Coins from Big Decimal value.
 *
 * @author Haaris Iqbal
 */

package com.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Change {
  // Key Variables.
  private BigDecimal money;
  private HashMap<Coins, Integer> result = new HashMap<Coins, Integer>();

  // Instantiation Variables.
  private BigDecimal quarter = new BigDecimal("25").setScale(2, RoundingMode.HALF_UP);
  private BigDecimal dime = new BigDecimal("10").setScale(2, RoundingMode.HALF_UP);
  private BigDecimal nickel = new BigDecimal("5").setScale(2, RoundingMode.HALF_UP);
  private BigDecimal penny = new BigDecimal("1").setScale(2, RoundingMode.HALF_UP);

  /**
   * Instantiates a new Change.
   *
   * @param money the money to get change for.
   */
  public Change(BigDecimal money) {
    this.money = money.setScale(2, RoundingMode.HALF_UP);

    populateHashMap();
    calculate();
  }

  // Getter methods acting as accessors.

  /**
   * Gets change.
   *
   * @return a HashMap of quarters, dimes, nickels and pennies.
   */
  public HashMap<Coins, Integer> getChange() {
    return result;
  }

  /**
   * Gets quarters.
   *
   * @return the number of quarters.
   */
  public Integer getQuarters() {
    return result.get(Coins.QUARTER);
  }

  /**
   * Gets dimes.
   *
   * @return the number of dimes.
   */
  public Integer getDimes() {
    return result.get(Coins.DIME);
  }

  /**
   * Gets nickels.
   *
   * @return the number of nickels.
   */
  public Integer getNickels() {
    return result.get(Coins.NICKEL);
  }

  /**
   * Gets pennies.
   *
   * @return the number of pennies.
   */
  public Integer getPennies() {
    return result.get(Coins.PENNY);
  }

  // Private methods used internally.

  /**
   * To calculate each number of quarters, dimes, nickels and pennies.
   */
  private void calculate() {
    int remainder = money.intValue();
    int start = 0;

    // First, determine case to work down from.
    if (money.compareTo(penny) >= 0) {
      start = 4; // Starting from penny case.
    }

    if (money.compareTo(nickel) >= 0) {
      start = 3; // Starting from nickel case.
    }

    if (money.compareTo(dime) >= 0) {
      start = 2; // Starting from dime case.
    }

    if (money.compareTo(quarter) >= 0) {
      start = 1; // Starting from quarter case.
    }

    // Then, using cascading switch statement to calculate change.
    switch (start) {
      case 1:
        remainder = money.remainder(quarter).intValue();
        result.put(Coins.QUARTER, (money.intValue()-remainder)/25);
        money = money.subtract(new BigDecimal(money.intValue()-remainder)); // Update money for next case.
      case 2:
        remainder = money.remainder(dime).intValue();
        result.put(Coins.DIME, (money.intValue()-remainder)/10);
        money = money.subtract(new BigDecimal(money.intValue()-remainder)); // Update money for next case.
      case 3:
        remainder = money.remainder(nickel).intValue();
        result.put(Coins.NICKEL, (money.intValue()-remainder)/5);
        // Money does not need to be updated now, as using remainder for last case.
      case 4:
        result.put(Coins.PENNY, result.get(Coins.PENNY)+remainder);
        break;
    }
  }

  /**
   * To initialize results HashMap.
   */
  private void populateHashMap() {
    // Starts at zero change.
    result.put(Coins.QUARTER, 0);
    result.put(Coins.DIME, 0);
    result.put(Coins.NICKEL, 0);
    result.put(Coins.PENNY, 0);
  }
}
