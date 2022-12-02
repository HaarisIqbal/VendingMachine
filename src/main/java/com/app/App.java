/**
 * Wiley Edge Project 3, November 2022.
 * App class, to call "execute" method and start program.
 *
 * @author Haaris Iqbal
 */

package com.app;

import com.controller.VendingMachineController;
import com.dao.AuditDAO;
import com.dao.AuditDAOFileImpl;
import com.dao.VendingMachineDAO;
import com.dao.VendingMachineDAOFileImpl;
import com.service.VendingMachineServiceLayer;
import com.service.VendingMachineServiceLayerImpl;
import com.ui.UserIO;
import com.ui.UserIOConsoleImpl;
import com.ui.View;

public class App {
  /**
   * The entry point of application.
   *
   * @param args the input arguments.
   */
  public static void main(String[] args) {
    execute();
  } // end of "main" method.

  /**
   * Private method to start the program.
   */
  private static void execute() {
    // Key Variables for Dependency Injection.
    VendingMachineDAO vmDAO = new VendingMachineDAOFileImpl();
    AuditDAO auditDAO = new AuditDAOFileImpl();
    VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayerImpl(vmDAO, auditDAO);

    UserIO io = new UserIOConsoleImpl();
    View view = new View(io);

    // A new Vending Machine controller.
    VendingMachineController controller = new VendingMachineController(serviceLayer, view);

    // Starting program.
    controller.runProgram();
  } // end of "execute" method.
}
