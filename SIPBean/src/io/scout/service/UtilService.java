package io.scout.service;

/**
 * @author DEV Scout
 */
public class UtilService {

  public UtilService() {}

  public String returnCode(long id, String sufix) {
    String code = null;
    StringBuilder builder = new StringBuilder("");
    builder.append("DBT00");
    builder.append(String.valueOf(id));
    builder.append("00");
    builder.append(sufix);
    code = builder.toString();
    return code;
  }
}
