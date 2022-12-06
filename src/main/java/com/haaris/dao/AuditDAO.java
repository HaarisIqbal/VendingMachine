package com.haaris.dao;

public interface AuditDAO {
  void createAuditFile();

  void writeAuditEntry(String entry);
}
