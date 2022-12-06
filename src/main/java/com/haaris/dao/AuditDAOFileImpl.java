/**
 * Wiley Edge Project 3, November 2022.
 * AuditDAO class, to access DTOs and to write to audit file.
 *
 * @author Haaris Iqbal
 */

package com.haaris.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditDAOFileImpl implements AuditDAO {
  private final String AUDIT_LOC = "src/main/java/com/haaris/storage/Audit.txt";
  private File auditFile = new File(AUDIT_LOC);;

  /**
   * Initialize Audit file for storage of audits.
   */
  @Override
  public void createAuditFile() {
    try {
      if (auditFile.createNewFile()) {
        System.out.println("File created: " + auditFile.getName());
      } else {
        System.out.println("Initialization message: " + auditFile.getName() + " already exists.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add a new Audit entry to audit file.
   *
   * @param entry the entry to be written.
   */
  @Override
  public void writeAuditEntry(String entry) {
    PrintWriter out;

    try {
      out = new PrintWriter(new FileWriter(auditFile, true));

      LocalDateTime timestamp = LocalDateTime.now();
      out.println(timestamp.toString() + " : " + entry);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
