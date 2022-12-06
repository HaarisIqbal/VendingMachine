/**
 * Wiley Edge Project 3, November 2022.
 * Controller class, to act as the interface between the UI and Service Layer.
 *
 * @author Haaris Iqbal
 */

package com.haaris.controller;

import com.haaris.dto.Coins;
import com.haaris.service.VendingMachineServiceLayer;
import com.haaris.ui.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class VendingMachineController {
  // Initialization variables.
  VendingMachineServiceLayer serviceLayer;
  View view;

  // Controller variables.
  private HashMap<String, BigDecimal> items = new HashMap<String, BigDecimal>();
  private boolean programLoop = true;
  String input;
  BigDecimal money = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
  HashMap<String, String> commands = new HashMap<String, String>();

  /**
   * Instantiates a new Vending machine controller.
   *
   * @param serviceLayer the service layer to be injected.
   * @param view         the view to be injected.
   */
  public VendingMachineController(VendingMachineServiceLayer serviceLayer, View view) {
    this.serviceLayer = serviceLayer;
    this.view = view;
  }

  /**
   * Main structure of the program.
   */
  public void runProgram() {
    serviceLayer.initializeFiles(); // Will use the Service Layer to access the DAOs.
    initializeCommands();

    // Initialization.
    getItems();
    view.introduction(items);
    getMoney();

    // Main loop.
    while (programLoop) {
      view.vending(money);
      input = view.getInput();
      inputCase(input);
    }
  }

  // Private methods relating to user io logic.

  /**
   * Private method for getting initial money input.
   */
  private void getMoney() {
    boolean invalid = true;

    while (invalid) {
      input = view.getInput();

      // Stop if exit request.
      if (input.equals("exit")) {
        view.exit(null);
        invalid = false; // Ending loop after output message.
        programLoop = false; // Main program will not start.
        continue;
      }

      // Stop if money input is valid.
      if (isValid(input)) {
        invalid = false;
      } else {
        view.unknown(input, 1);
      }
    }
  }

  /**
   * Private method to determine if money input is valid, and to set money to input.
   *
   * @param input the input from the UI.
   */
  private boolean isValid(String input) {
    try {
      money = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);;
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Private method making decisions based on given input - for initial part of program.
   *
   * @param input the input from the UI.
   */
  private void inputCase(String input) {
    switch (input) {
      case "exit":
        view.exit(change());
        programLoop = false; // Ending loop after output message.
        break;
      case "help":
        view.help(commands);
        break;
      case "list":
        view.list(items);
        break;
      default:
        processItem(input);
    }
  }

  /**
   * Private method to check input and money validity before purchase.
   *
   * @param input the input from the user.
   */
  private void processItem(String input) {
    boolean notFound = true;

    // First, check if valid input.
    for (String item : items.keySet()) {
      if (item.toLowerCase().equals(input)) {
        notFound = false;

        // Next, check if funds are sufficient.
        if (money.compareTo(items.get(item)) >= 0) {
          money = money.subtract(items.get(item)); // Subtract amount.
          purchase(item);
        } else {
          view.insufficientFunds(item);
        }

      }
    }

    // Update available items list.
    getItems();

    // When input is not found in items.
    if (notFound) {
      view.unknown(input, 0);
    }
  }

  /**
   * Private method to initialize a HashSet of valid commands.
   */
  private void initializeCommands() {
    commands.clear();

    commands.put("help", "To display commands.");
    commands.put("list", "To show all items again.");
    commands.put("exit", "To quit the program.");
  }

  // Private methods relating to service layer logic.

  /**
   * Private method to get items and their respective prices.
   */
  private void getItems() {
    items.clear(); // Must clear the HashMap to reflect amount updates in Items.txt.

    items = serviceLayer.getAvailableItems();
  }

  /**
   * Private method to complete purchase by updating backend file and controller HashMap.
   */
  private void purchase(String item) {
    // Ask service layer to update item file.
    serviceLayer.updateItemFile(item);

    // Display successful purchase on view maybe with amount of items left.
    view.success(item, serviceLayer.getItemAmount(item), change());
  }

  /**
   * Private method to calculate change.
   *
   * @return a HashMap of coins.
   */
  private HashMap<Coins, Integer> change() {
    return serviceLayer.getChange(money.multiply(new BigDecimal("100")));
  }
}
