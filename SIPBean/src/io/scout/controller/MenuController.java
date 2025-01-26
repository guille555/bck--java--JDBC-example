package io.scout.controller;

import io.scout.model.GroupRol;
import io.scout.model.Menu;
import io.scout.service.MenuService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class MenuController {

  public MenuController() {}

  private GroupRol getGroupRolLoaded(String code) {
    GroupRol data = new GroupRol();
    data.setCode(code);
    return data;
  }

  private Menu buildData(String code, String name, String codeGroupRol) {
    GroupRol groupRol = this.getGroupRolLoaded(codeGroupRol);
    Menu data = new Menu();
    data.setName(name);
    data.setCode(code);
    data.setGroupRol(groupRol);
    return data;
  }

  public SystemResponse save(String name, String codeGroupRol) {
    Menu data = null;
    MenuService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new MenuService();
      data = this.buildData(null, name, codeGroupRol);
      service.save(data);
      response = util.returnSuccessResponse();
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SAVE: " + exc.getMessage());
      response = util.returnErrorResponse();
      return response;
    }
  }

  public SystemResponse update(String code, String name, String codeGroupRol) {
    Menu data = null;
    MenuService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new MenuService();
      data = this.buildData(code, name, codeGroupRol);
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
    Menu data = null;
    MenuService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new MenuService();
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
    Menu data = null;
    MenuService service = null;
    SystemResponse response = null;
    UtilController util = null;
    try {
      util = new UtilController();
      service = new MenuService();
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

  public Menu findById(short id) {
    MenuService service = new MenuService();
    Menu data = service.findById(id);
    return data;
  }

  public Menu findByCode(String code) {
    MenuService service = new MenuService();
    Menu data = service.findByCode(code);
    return data;
  }

  public List<Menu> findAll() {
    MenuService service = new MenuService();
    List<Menu> list = service.findAll();
    return list;
  }
}
