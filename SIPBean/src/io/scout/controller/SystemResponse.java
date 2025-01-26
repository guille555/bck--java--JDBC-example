package io.scout.controller;

/**
 * @author DEV Scout
 */
public class SystemResponse {

  private byte code;
  private String message;

  public SystemResponse() {}

  public byte getCode() {
    return code;
  }

  public void setCode(byte code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
