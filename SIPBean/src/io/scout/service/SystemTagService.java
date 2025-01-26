package io.scout.service;

import io.scout.dao.IBasicDAO;
import io.scout.dao.SystemTagDAOImp;
import io.scout.model.SystemTag;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class SystemTagService {

  public SystemTagService() {}

  private SystemTag returnSystemTagLoaded(SystemTag data) {
    SystemTag systemTag = new SystemTag();
    systemTag.setName(data.getName().toUpperCase());
    systemTag.setSerialNumber(data.getSerialNumber().toUpperCase());
    return  systemTag;
  }

  public void save(SystemTag data) {
    int id = 0;
    String code = null;
    SystemTag systemTag = null;
    UtilService util = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      util = new UtilService();
      systemTag = this.returnSystemTagLoaded(data);
      id = dao.save(systemTag);
      code = util.returnCode(id, "STG");
      dao.addCode(id, code);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void update(SystemTag data) {
    SystemTag systemTag = null;
    SystemTag systemTagUpdated = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      systemTag = this.findByCode(data.getCode().toUpperCase());
      if ((systemTag.isFlagState()) && (systemTag.getId() > 0)) {
        systemTagUpdated = this.returnSystemTagLoaded(data);
        systemTagUpdated.setId(systemTag.getId());
        dao.update(systemTagUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void shift(SystemTag data) {
    SystemTag systemTag = null;
    SystemTag systemTagShifted = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      systemTag = this.findByCode(data.getCode().toUpperCase());
      if (systemTag.getId() > 0) {
        systemTagShifted = new SystemTag();
        systemTagShifted.setId(systemTag.getId());
        systemTagShifted.setFlagState(!systemTag.isFlagState());
        dao.shift(systemTagShifted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SHIFT: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void delete(SystemTag data) {
    SystemTag systemTag = null;
    SystemTag systemTagDeleted = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      systemTag = this.findByCode(data.getCode().toUpperCase());
      if (systemTag.getId() > 0) {
        systemTagDeleted = new SystemTag();
        systemTagDeleted.setId(systemTag.getId());
        dao.delete(systemTagDeleted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public SystemTag findById(short id) {
    SystemTag systemTag = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      systemTag = dao.findById(id);
      return systemTag;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      systemTag = new SystemTag();
      return systemTag;
    }
  }

  public SystemTag findByCode(String code) {
    SystemTag systemTag = null;
    IBasicDAO<SystemTag> dao = null;
    try {
      dao = new SystemTagDAOImp();
      systemTag = dao.findByCode(code.toUpperCase());
      return systemTag;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      systemTag = new SystemTag();
      return systemTag;
    }
  }

  public List<SystemTag> findAll() {
    IBasicDAO<SystemTag> dao = null;
    List<SystemTag> list = null;
    try {
      dao = new SystemTagDAOImp();
      list = dao.findAll();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      list = new ArrayList<SystemTag>();
      return list;
    }
  }
}
