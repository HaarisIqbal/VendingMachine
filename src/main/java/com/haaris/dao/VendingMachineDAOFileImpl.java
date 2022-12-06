/**
 * Wiley Edge Project 3, November 2022.
 * VendingMachineDAO class, to access DTOs and to write to item file.
 *
 * @author Haaris Iqbal
 */

package com.haaris.dao;

import com.haaris.dto.Item;

import java.io.*;
import java.util.HashSet;

/**
 * The type Vending machine dao file.
 */
public class VendingMachineDAOFileImpl implements VendingMachineDAO {
  // Initialization variables.
  private final String ITEMS_LOC = "src/main/java/com/haaris/storage/Items.txt";
  private File itemFile = new File(ITEMS_LOC);

  // DAO variables.
  private HashSet<Item> allItems = new HashSet<Item>();

  /**
   * Initialize Item file for storage of items.
   */
  @Override
  public void createItemFile() {
    try {
      if (itemFile.createNewFile()) {
        System.out.println("File created: " + itemFile.getName());
      } else {
        System.out.println("Initialization message: " + itemFile.getName() + " already exists.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the current item list.
   *
   * @return the 'allItems' HashSet containing all items in Items file.
   */
  @Override
  public HashSet<Item> getItems() {
    readItemFile(); // Get updated set of all items.

    return this.allItems;
  }

  /**
   * Updating item quantity in file.
   *
   * @param itemName item to be taken from 'vending machine'.
   */
  @Override
  public void takeOne(String itemName) {
    // Get fresh copy of Item HashSet.
    readItemFile();

    // 1. Create duplicate HashSet.
    HashSet<Item> allItemsUpdated = new HashSet<Item>();

    // 1.1 Duplication without pointers.
    for (Item i : this.allItems) {
      if (i.getName().equals(itemName)) {
        i.setAmount(i.getAmount()-1); // Critically, updated Item is lowered in quantity.
      }

      allItemsUpdated.add(i);
    }

    // 2. Remove all items.
    eraseItemFile();

    // 3. Finally, add (updated) items back.
    for (Item i : allItemsUpdated) {
      writeItemFile(i);
    }
  }

  // Private methods interacting with Storage.

  /**
   * Private method to get all items from Items file.
   */
  private void readItemFile() {
    try {
      FileReader fileReader = new FileReader(itemFile); // Read the file itself.
      BufferedReader bufferedReader = new BufferedReader(fileReader); // Line per line reading.

      // Clear HashSet of current items.
      allItems.clear();

      // Variables to process data in item file.
      String lineFromFile = "";
      String[] line;
      Item item;

      // Iterating though each line in Item file.
      lineFromFile = bufferedReader.readLine();
      while (lineFromFile != null) {
        line = lineFromFile.split(",");

        // Using raw data to construct an Item.
        item = new Item(line[0], line[1], Integer.parseInt(line[2]));

        // Adding to HashSet.
        allItems.add(item);

        // Move to next iteration.
        lineFromFile = bufferedReader.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Private method to add a new Item to the Item File.
   *
   * @param item the Item to be added to the Item File.
   */
  private void writeItemFile(Item item) {
    readItemFile(); // to get HashSet of current Items.

    // Preparing item attributes for Item file.
    String itemString = item.getName() + "," + item.getPrice() + "," + item.getAmount();

    // Add item to Item file.
    try {
      FileWriter fileWriter = new FileWriter(itemFile, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      if (!allItems.isEmpty()) {
        bufferedWriter.newLine();
      }
      bufferedWriter.write(itemString);

      // Close.
      bufferedWriter.flush();
      bufferedWriter.close();

      // Finally, add new item to HashSet.
      allItems.add(item);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Private method to erase item file.
   */
  private void eraseItemFile() {
    // Erase all in item file.
    try {
      FileWriter fileWriter = new FileWriter(itemFile, false);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      // Since append is set to false, this will erase text file of all information.
      bufferedWriter.write("");

      // Close.
      bufferedWriter.flush();
      bufferedWriter.close();

      // Finally, erase HashSet.
      allItems.clear();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
