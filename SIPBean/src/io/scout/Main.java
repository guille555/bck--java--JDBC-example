package io.scout;

import io.scout.persistence.DBPersistence;
import io.scout.persistence.InitDB;

/**
 * @author DEV Scout
 */
public class Main {

  public static void main(String[] args) {
    DBPersistence per = new DBPersistence();
    InitDB init = new InitDB();
    init.createTables();
    /* CODE TO TEST, HERE */
    per.closeDB();
  }
}
