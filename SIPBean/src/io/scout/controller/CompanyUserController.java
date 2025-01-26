package io.scout.controller;

import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import io.scout.service.CompanyUserService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class CompanyUserController {

  public CompanyUserController() {}

  private SystemTag getSystemTagLoaded(String code) {
    SystemTag data = new SystemTag();
    data.setCode(code);
    return data;
  }

  private GroupRol getGroupRolLoaded(String code) {
    GroupRol data = new GroupRol();
    data.setCode(code);
    return data;
  }

  private CompanyUser buildData(String code, String username, String password, String codeSystemTag, String codeGroupRol) {
    SystemTag systemTag = this.getSystemTagLoaded(codeSystemTag);
    GroupRol groupRol = this.getGroupRolLoaded(codeGroupRol);
    CompanyUser data = new CompanyUser();
    data.setCode(code);
    data.setUsername(username);
    data.setPassword(password);
    data.setSystemTag(systemTag);
    data.setGroupRol(groupRol);
    return data;
  }

  public SystemResponse save(String username, String password, String codeSystemTag, String codeGroupRol) {
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new CompanyUserService();
      data = this.buildData(null, username, password, codeSystemTag, codeGroupRol);
      service.save(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SAVE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse update(String code, String username, String password, String codeSystemTag, String codeGroupRol) {
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new CompanyUserService();
      data = this.buildData(code, username, password, codeSystemTag, codeGroupRol);
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
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new CompanyUserService();
      data = this.buildData(code, null, null, null, null);
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
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new CompanyUserService();
      data = this.buildData(code, null, null, null, null);
      service.delete(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER DELETE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public CompanyUser findById(long id) {
    CompanyUserService service = new CompanyUserService();
    CompanyUser data = service.findById(id);
    return data;
  }

  public CompanyUser findByCode(String code) {
    CompanyUserService service = new CompanyUserService();
    CompanyUser data = service.findByCode(code);
    return data;
  }

  public List<CompanyUser> findAll() {
    CompanyUserService service = new CompanyUserService();
    List<CompanyUser> list = service.findAll();
    return list;
  }
}
