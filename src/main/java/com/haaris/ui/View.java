/**
 * Wiley Edge Project 3, November 2022.
 * View class, to handle user interaction generally.
 *
 * @author Haaris Iqbal
 */

package com.haaris.ui;

import com.haaris.dto.Coins;

import java.math.BigDecimal;
import java.util.HashMap;

public class View {
  // View variable.
  private UserIO io;

  /**
   * Instantiates a new View.
   *
   * @param io the io to be injected.
   */
  public View(UserIO io) {
    this.io = io;
  }

  /**
   * Introduction to program.
   *
   * @param items the available items in the vending machine.
   */
  public void introduction(HashMap<String, BigDecimal> items) {
    io.outln("\n*** Welcome to the Vending Machine Program! ***");

    list(items);

    io.outln("\nTo begin, please input an amount of money ($). Or type 'exit' to close the program.");
  }

  /**
   * Repeated vending message.
   *
   * @param money the amount of money the user has.
   */
  public void vending(BigDecimal money) {
    io.outln("\nYou have: $" + money);

    io.outln("Please type the name of the item you would like! Or type 'help' for some commands.");
  }

  /**
   * List of available items in the vending machine.
   *
   * @param items the items to be listed.
   */
  public void list(HashMap<String, BigDecimal> items) {
    io.outln("\nThese are all of the available items and their prices:");

    for (String key : items.keySet()) {
      io.outln(key + " [$" + items.get(key) + "]");
    }
  }

  /**
   * Offering help on what to type.
   *
   * @param commands the commands available to the user.
   */
  public void help(HashMap<String, String> commands) {
    io.outln("\nIf you are stuck, try some of the following: ");
    for (String key : commands.keySet()) {
      io.outln("-> " + key + " : " + commands.get(key));
    }
  }

  /**
   * Gets input from user.
   *
   * @return the input.
   */
  public String getInput() {
    return io.input(); // Return without case sensitivity.
  }

  /**
   * Successful purchase of an item.
   *
   * @param item   the item that has been purchased
   * @param amount the quantity of the item left in the vending machine.
   * @param change the users change.
   */
  public void success(String item, int amount, HashMap<Coins, Integer> change) {
    io.outln("\n** You have purchased " + item + "! **");

    io.out("Your change is: ");
    for (Coins c : change.keySet()) {
      if (change.get(c) == 1) {
        io.out(change.get(c) + " " + c.toString().toLowerCase() + " ");
      } else if (change.get(c) > 1) {
        io.out(change.get(c) + " " + c.toString().toLowerCase() + "s ");
      }
    }

    if (amount == 1) {
      io.outln("\n[Btw, there's " + amount + " " + item + " left]");
    } else {
      io.outln("\n[Btw, there's " + amount + " " + item + "s left]");
    }
  }

  /**
   * Insufficient funds during purchase attempt.
   *
   * @param item the item that could not be purchased.
   */
  public void insufficientFunds(String item) {
    io.outln("\nYou do not have enough money to buy " + item + "!");
  }

  /**
   * Error for displaying an unknown input.
   *
   * @param input the unknown input that was typed.
   * @param c     additional error case to display.
   */
  public void unknown(String input, int c) {
    io.outln("\n'" + input + "' is not a valid input!");
    switch (c) {
      case 1:
        io.outln("Please input an amount of money, or type type 'exit' to close the program.\n");
        break;
    }
  }

  /**
   * Shut Scanner process and close the program.
   *
   * @param change any remaining change to give back to user.
   */
  public void exit(HashMap<Coins, Integer> change) {
    if (change != null) {
      io.out("\nHere is your remaining change: ");
      for (Coins c : change.keySet()) {
        if (change.get(c) == 1) {
          io.out(change.get(c) + " " + c.toString().toLowerCase() + " ");
        } else if (change.get(c) > 1) {
          io.out(change.get(c) + " " + c.toString().toLowerCase() + "s ");
        }
      }

      io.outln("");
    }

    io.outln("\nShutting program now!\n");

    io.close();
  }
}
