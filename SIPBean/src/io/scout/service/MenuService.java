package io.scout.service;

import io.scout.dao.IBasicDAO;
import io.scout.dao.MenuDAOImp;
import io.scout.model.GroupRol;
import io.scout.model.Menu;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class MenuService {

  public MenuService() {}

  private GroupRol findGroupRolByCode(String code) {
    GroupRolService service = new GroupRolService();
    GroupRol groupRol = service.findByCode(code.toUpperCase());
    return groupRol;
  }

  private Menu returnObjectLoaded(Menu data) {
    GroupRol groupRol = this.findGroupRolByCode(data.getGroupRol().getCode());
    Menu menu = new Menu();
    menu.setName(data.getName().toUpperCase());
    menu.setGroupRol(groupRol);
    return menu;
  }

  public void save(Menu data) {
    int id = 0;
    String code = null;
    Menu menu = null;
    UtilService util = null;
    IBasicDAO<Menu> dao = null;
    try {
      util = new UtilService();
      dao = new MenuDAOImp();
      menu = this.returnObjectLoaded(data);
      id = dao.save(menu);
      code = util.returnCode(id, "MNU");
      dao.addCode(id, code);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void update(Menu data) {
    Menu menu = null;
    Menu menuUpdated = null;
    IBasicDAO<Menu> dao = null;
    try {
      dao = new MenuDAOImp();
      menu = this.findByCode(data.getCode());
      if ((menu.isFlagState()) && (menu.getId() > 0)) {
        menuUpdated = this.returnObjectLoaded(data);
        menuUpdated.setId(menu.getId());
        dao.update(menuUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void shift(Menu data) {
    Menu menu = null;
    Menu menuShifted = null;
    IBasicDAO<Menu> dao = null;
    try {
      dao = new MenuDAOImp();
      menu = this.findByCode(data.getCode());
      if (menu.getId() > 0) {
        menuShifted = new Menu();
        menuShifted.setId(menu.getId());
        menuShifted.setFlagState(!menu.isFlagState());
        dao.shift(menuShifted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SHIFT: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void delete(Menu data) {
    Menu menu = null;
    Menu menuDeleted = null;
    IBasicDAO<Menu> dao = null;
    try {
      dao = new MenuDAOImp();
      menu = this.findByCode(data.getCode());
      if (menu.getId() > 0) {
        menuDeleted = new Menu();
        menuDeleted.setId(menu.getId());
        dao.delete(menuDeleted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public Menu findById(short id) {
    Menu menu = null;
    IBasicDAO<Menu> dao = null;
    try {
      dao = new MenuDAOImp();
      menu = dao.findById(id);
      return menu;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      menu = new Menu();
      return menu;
    }
  }

  public Menu findByCode(String code) {
    Menu menu = null;
    IBasicDAO<Menu> dao = null;
    try {
      dao = new MenuDAOImp();
      menu = dao.findByCode(code.toUpperCase());
      return menu;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      menu = new Menu();
      return menu;
    }
  }

  public List<Menu> findAll() {
    IBasicDAO<Menu> dao = null;
    List<Menu> list = null;
    try {
      dao = new MenuDAOImp();
      list = dao.findAll();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    }
  }
}
