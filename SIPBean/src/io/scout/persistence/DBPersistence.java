package io.scout.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author DEV Scout
 */
public class DBPersistence {

  public DBPersistence() {}

  public Connection getConnection() {
    String url = null;
    Connection connection = null;
    try {
      url = "jdbc:derby:memory:app/app_db;create=true;user=dev;password=root";
      connection = DriverManager.getConnection(url);
      connection.setAutoCommit(false);
      return connection;
    } catch (SQLException exc) {
      System.err.println("ERROR SQL: " + exc.getMessage());
      return null;
    } catch (Exception exc) {
      System.err.println("ERROR STD: " + exc.getMessage());
      return null;
    }
  }

  public void closeDB() {
    String url = null;
    Connection connection = null;
    try {
      url = "jdbc:derby:memory:app/app_db;shutdown=true;user=dev;password=root";
      connection = DriverManager.getConnection(url);
    } catch (SQLException exc) {
      System.err.println("SQL ERROR: error returned for closing the data base");
    }
  }
}
