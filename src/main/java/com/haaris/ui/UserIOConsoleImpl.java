/**
 * Wiley Edge Project 3, November 2022.
 * UserIOConsoleImpl class, used by View class to handle raw interaction with the console.
 *
 * @author Haaris Iqbal
 */

package com.haaris.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
  // UserIO variables.
  private String line;
  private final Scanner scanner = new Scanner(System.in);

  /**
   * To obtain the input.
   *
   * @return the input String as lower-case.
   */
  @Override
  public String input() {
    System.out.print(">> ");

    line = scanner.nextLine();

    return line.toLowerCase(); // Commands are always treated as being in lower case.
  }

  /**
   * To obtain input without altering its case sensitivity.
   *
   * @return the unaltered input String.
   */
  @Override
  public String inputCaseSensitive() {
    System.out.print(">> ");

    line = scanner.nextLine();

    return line;
  }

  /**
   * To display output without creating a new line.
   *
   * @param out the output to be displayed.
   */
  @Override
  public void out(String out) {
    System.out.print(out);
  }

  /**
   * To display output while also creating a new line.
   *
   * @param out the output to be displayed.
   */
  @Override
  public void outln(String out) {
    System.out.println(out);
  }

  /**
   * To close the scanner upon quitting the program.
   */
  @Override
  public void close() {
    scanner.close();
  }
}
