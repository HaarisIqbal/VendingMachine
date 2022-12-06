/**
 * Wiley Edge Project 3, November 2022.
 * App class, to call Spring for Dependency Injection and start program.
 *
 * @author Haaris Iqbal
 */

package com.haaris;

import com.haaris.controller.VendingMachineController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
  /**
   * The entry point of application.
   *
   * @param args the input arguments.
   */
  public static void main(String[] args) {
    // Spring DI.
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    // A new Vending Machine controller.
    VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

    // Starting program.
    controller.runProgram();
  } // end of "main" method.
}
