package io.scout.service;

import io.scout.dao.CompanyUserDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class CompanyUserService {

  public CompanyUserService() {}

  private SystemTag findSystemTagByCode(String code) {
    SystemTagService service = new SystemTagService();
    SystemTag systemTag = service.findByCode(code.toUpperCase());
    return systemTag;
  }

  private GroupRol findGroupRolByCode(String code) {
    GroupRolService service = new GroupRolService();
    GroupRol object = service.findByCode(code.toUpperCase());
    return object;
  }

  private CompanyUser returnObjectLoaded(CompanyUser data) {
    SystemTag systemTag = this.findSystemTagByCode(data.getSystemTag().getCode());
    GroupRol groupRol = this.findGroupRolByCode(data.getGroupRol().getCode());
    CompanyUser companyUser = new CompanyUser();
    companyUser.setUsername(data.getUsername());
    companyUser.setPassword(data.getPassword());
    companyUser.setSystemTag(systemTag);
    companyUser.setGroupRol(groupRol);
    return companyUser;
  }

  public void save(CompanyUser data) {
    int id = 0;
    String code = null;
    CompanyUser companyUser = null;
    UtilService util = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      util = new UtilService();
      dao = new CompanyUserDAOImp();
      companyUser = this.returnObjectLoaded(data);
      id = dao.save(companyUser);
      code = util.returnCode(id, "CUR");
      dao.addCode(id, code);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void update(CompanyUser data) {
    CompanyUser companyUser = null;
    CompanyUser companyUserUpdated = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      dao = new CompanyUserDAOImp();
      companyUser = this.findByCode(data.getCode());
      if ((companyUser.isFlagState()) && (companyUser.getId() > 0)) {
        companyUserUpdated = this.returnObjectLoaded(data);
        companyUserUpdated.setId(companyUser.getId());
        dao.update(companyUserUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void shift(CompanyUser data) {
    CompanyUser companyUser = null;
    CompanyUser companyUserShifted = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      dao = new CompanyUserDAOImp();
      companyUser = this.findByCode(data.getCode());
      if (companyUser.getId() > 0) {
        companyUserShifted = new CompanyUser();
        companyUserShifted.setId(companyUser.getId());
        companyUserShifted.setFlagState(!companyUser.isFlagState());
        dao.shift(companyUserShifted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SHIFT: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public void delete(CompanyUser data) {
    CompanyUser companyUser = null;
    CompanyUser companyUserDeleted = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      dao = new CompanyUserDAOImp();
      companyUser = this.findByCode(data.getCode());
      if (companyUser.getId() > 0) {
        companyUserDeleted = new CompanyUser();
        companyUserDeleted.setId(companyUser.getId());
        dao.delete(companyUserDeleted);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  public CompanyUser findById(long id) {
    CompanyUser object = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      dao = new CompanyUserDAOImp();
      object = dao.findById(id);
      return object;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      object = new CompanyUser();
      return object;
    }
  }

  public CompanyUser findByCode(String code) {
    CompanyUser object = null;
    IBasicDAO<CompanyUser> dao = null;
    try {
      dao = new CompanyUserDAOImp();
      object = dao.findByCode(code.toUpperCase());
      return object;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      object = new CompanyUser();
      return object;
    }
  }

  public List<CompanyUser> findAll() {
    IBasicDAO<CompanyUser> dao = null;
    List<CompanyUser> list = null;
    try {
      dao = new CompanyUserDAOImp();
      list = dao.findAll();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FETCHING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    }
  }
}
