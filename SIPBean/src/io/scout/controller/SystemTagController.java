package io.scout.controller;

import io.scout.model.SystemTag;
import io.scout.service.SystemTagService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class SystemTagController {

  public SystemTagController() {}

  private SystemTag buildData(String code, String name, String serialNumber) {
    SystemTag data = new SystemTag();
    data.setCode(code);
    data.setName(name);
    data.setSerialNumber(serialNumber);
    return data;
  }

  public SystemResponse save(String name, String serialNumber) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse response = null;
    UtilController util = new UtilController();
    try {
      service = new SystemTagService();
      data = this.buildData(null, name, serialNumber);
      service.save(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SAVE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse update(String code, String name, String serialNumber) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse response = null;
    UtilController util = new UtilController();
    try {
      service = new SystemTagService();
      data = this.buildData(code, name, serialNumber);
      service.update(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER UPDATE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse shift(String code) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse response = null;
    UtilController util = new UtilController();
    try {
      service = new SystemTagService();
      data = this.buildData(code, null, null);
      service.shift(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SHIFT: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse delete(String code) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse response = null;
    UtilController util = new UtilController();
    try {
      service = new SystemTagService();
      data = this.buildData(code, null, null);
      service.delete(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER DELETE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemTag findById(short id) {
    SystemTagService service = new SystemTagService();
    SystemTag data = service.findById(id);
    return data;
  }

  public SystemTag findByCode(String code) {
    SystemTagService service = new SystemTagService();
    SystemTag data = service.findByCode(code);
    return data;
  }

  public List<SystemTag> findAll() {
    SystemTagService service = new SystemTagService();
    List<SystemTag> list = service.findAll();
    return list;
  }
}
