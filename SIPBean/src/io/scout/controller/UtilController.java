package io.scout.controller;

/**
 * @author DEV Scout
 */
public class UtilController {

  public UtilController() {}

  public SystemResponse returnSuccessResponse() {
    SystemResponse response = new SystemResponse();
    response.setCode((byte) 200);
    response.setMessage("ok");
    return response;
  }

  public SystemResponse returnErrorResponse() {
    SystemResponse response = new SystemResponse();
    response.setCode((byte) 200);
    response.setMessage("ok");
    return response;
  }
}