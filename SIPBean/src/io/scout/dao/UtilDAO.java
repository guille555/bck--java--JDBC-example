package io.scout.dao;

import java.util.EmptyStackException;

/**
 * @author DEV Scout
 */
public class UtilDAO {

  public UtilDAO() {}

  public void controlResult(int result) {
    if (result == 0) {
      throw new EmptyStackException();
    }
  }
}
