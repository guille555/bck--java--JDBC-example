package io.scout.service;

import io.scout.dao.GroupRolDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class GroupRolService {

  public GroupRolService() {}

  private SystemTag findSystemTagByCode(String code) {
    SystemTagService service = new SystemTagService();
    SystemTag systemTag = service.findByCode(code.toUpperCase());
    return systemTag;
  }

  private GroupRol returnObjectLoaded(GroupRol data) {
    SystemTag systemTag = this.findSystemTagByCode(data.getSystemTag().getCode().toUpperCase());
    GroupRol groupRol = new GroupRol();
    groupRol.setName(data.getName().toUpperCase());
    groupRol.setSystemTag(systemTag);
    return groupRol;
  }

  public void save(GroupRol data) {
    int id = 0;
    String code = null;
    GroupRol groupRol = null;
    UtilService util = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      util = new UtilService();
      dao = new GroupRolDAOImp();
      groupRol = this.returnObjectLoaded(data);
      id = dao.save(groupRol);
      code = util.returnCode(id, "GRL");
      dao.addCode(id, code);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void update(GroupRol data) {
    GroupRol groupRol = null;
    GroupRol groupRolUpdated = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      dao = new GroupRolDAOImp();
      groupRol = this.findByCode(data.getCode().toUpperCase());
      if ((groupRol.isFlagState()) && (groupRol.getId() > 0)) {
        groupRolUpdated = this.returnObjectLoaded(data);
        groupRolUpdated.setId(groupRol.getId());
        dao.update(groupRolUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void shift(GroupRol data) {
    GroupRol groupRol = null;
    GroupRol groupRolShifted = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      dao = new GroupRolDAOImp();
      groupRol = this.findByCode(data.getCode().toUpperCase());
      if (groupRol.getId() > 0) {
        groupRolShifted = new GroupRol();
        groupRolShifted.setId(groupRol.getId());
        groupRolShifted.setFlagState(!groupRol.isFlagState());
        dao.shift(groupRolShifted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SHIFT: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void delete(GroupRol data) {
    GroupRol groupRol = null;
    GroupRol groupRolDeleted = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      dao = new GroupRolDAOImp();
      groupRol = this.findByCode(data.getCode().toUpperCase());
      if (groupRol.getId() > 0) {
        groupRolDeleted = new GroupRol();
        groupRolDeleted.setId(groupRol.getId());
        dao.delete(groupRolDeleted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public GroupRol findById(short id) {
    GroupRol object = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      dao = new GroupRolDAOImp();
      object = dao.findById(id);
      return object;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      object = new GroupRol();
      return object;
    }
  }

  public GroupRol findByCode(String code) {
    GroupRol object = null;
    IBasicDAO<GroupRol> dao = null;
    try {
      dao = new GroupRolDAOImp();
      object = dao.findByCode(code.toUpperCase());
      return object;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      object = new GroupRol();
      return object;
    }
  }

  public List<GroupRol> findAll() {
    IBasicDAO<GroupRol> dao = null;
    List<GroupRol> list = null;
    try {
      dao = new GroupRolDAOImp();
      list = dao.findAll();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
      return list;
    }
  }
}
