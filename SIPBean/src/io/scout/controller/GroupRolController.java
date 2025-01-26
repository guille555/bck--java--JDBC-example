package io.scout.controller;

import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import io.scout.service.GroupRolService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class GroupRolController {

  public GroupRolController() {}

  private SystemTag getSystemTagLoaded(String code) {
    SystemTag data = new SystemTag();
    data.setCode(code);
    return data;
  }

  private GroupRol buildData(String code, String name, String codeSystemTag) {
    SystemTag systemTag = this.getSystemTagLoaded(codeSystemTag);
    GroupRol data = new GroupRol();
    data.setName(name);
    data.setCode(code);
    data.setSystemTag(systemTag);
    return data;
  }

  public SystemResponse save(String name, String codeSystemTag) {
    GroupRol data = null;
    GroupRolService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new GroupRolService();
      data = this.buildData(null, name, codeSystemTag);
      service.save(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SAVE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse update(String code, String name, String codeSystemTag) {
    GroupRol data = null;
    GroupRolService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new GroupRolService();
      data = this.buildData(code, name, codeSystemTag);
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
    GroupRol data = null;
    GroupRolService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new GroupRolService();
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
    GroupRol data = null;
    GroupRolService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new GroupRolService();
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

  public GroupRol findById(short id) {
    GroupRolService service = new GroupRolService();
    GroupRol data = service.findById(id);
    return data;
  }

  public GroupRol findByCode(String code) {
    GroupRolService service = new GroupRolService();
    GroupRol data = service.findByCode(code);
    return data;
  }

  public List<GroupRol> findAll() {
    GroupRolService service = new GroupRolService();
    List<GroupRol> list = service.findAll();
    return list;
  }
}
