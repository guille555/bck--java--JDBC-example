package io.scout.dao;

import io.scout.model.CompanyUser;
import io.scout.persistence.DBPersistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class CompanyUserDAOImp implements IBasicDAO<CompanyUser> {

  public CompanyUserDAOImp() {}

  private CompanyUser returnDefaultObject() {
    CompanyUser object = new CompanyUser();
    object.setId(0);
    object.setCode(null);
    object.setFlagState(false);
    object.setSystemTag(null);
    object.setGroupRol(null);
    return object;
  }

  private List<CompanyUser> returnListObjects(ResultSet results) {
    CompanyUser item = null;
    List<CompanyUser> list = null;
    try {
      list = new ArrayList<CompanyUser>();
      while (results.next()) {
        item = new CompanyUser();
        item.setId(results.getLong("company_user_id"));
        item.setCode(results.getString("company_user_code"));
        item.setUsername(results.getString("username"));
        item.setPassword(results.getString("password"));
        item.setFlagState(results.getBoolean("flag_state"));
        item.setRegisterDate(results.getDate("register_date"));
        item.setSystemTag(null);
        item.setGroupRol(null);
        list.add(item);
      }
      return list;
    } catch (SQLException exc) {
      item = this.returnDefaultObject();
      list = new ArrayList<CompanyUser>();
      list.add(item);
      return list;
    }
  }

  @Override
  public int save(CompanyUser object) {
    int result = 0;
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet resultSet = null;
    UtilDAO util = null;
    try {
      dbPersistence = new DBPersistence();
      util = new UtilDAO();
      sentence = "INSERT INTO company_user(username, password, flag_state, system_tag_id, group_rol_id) VALUES (?, ?, TRUE, ?, ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence, Statement.RETURN_GENERATED_KEYS);
      query.setString(1, object.getUsername());
      query.setString(2, object.getPassword());
      query.setShort(3, object.getSystemTag().getId());
      query.setShort(4, object.getGroupRol().getId());
      result = query.executeUpdate();
      util.controlResult(result);
      resultSet = query.getGeneratedKeys();
      while (resultSet.next()) {
        result = resultSet.getShort(1);
      }
      connection.commit();
      connection.endRequest();
      resultSet.close();
      query.close();
      connection.close();
      return result;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO SAVE: " + exc.getMessage());
      return 0;
    }
  }

  @Override
  public void addCode(long id, String code) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE company_user SET company_user_code = ? WHERE (flag_state = TRUE) AND (company_user_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      query.setLong(2, id);
      query.executeUpdate();
      connection.commit();
      connection.endRequest();
      query.close();
      connection.close();
    } catch (SQLException exc) {
      System.err.println("ERROR DAO UPDATE: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public void update(CompanyUser object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE company_user SET username = ?, password = ?, system_tag_id = ?, group_rol_id = ? WHERE (flag_state = TRUE) AND (company_user_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, object.getUsername());
      query.setString(2, object.getPassword());
      query.setShort(3, object.getSystemTag().getId());
      query.setShort(4, object.getGroupRol().getId());
      query.setLong(5, object.getId());
      query.executeUpdate();
      connection.commit();
      connection.endRequest();
      query.close();
      connection.close();
    } catch (SQLException exc) {
      System.err.println("ERROR DAO UPDATE: " + exc.getMessage());
    }
  }

  @Override
  public void shift(CompanyUser object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE company_user SET flag_state = ? WHERE (company_user_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setBoolean(1, object.isFlagState());
      query.setLong(2, object.getId());
      query.execute();
      connection.commit();
      connection.endRequest();
      query.close();
      connection.close();
    } catch (SQLException exc) {
      System.err.println("ERROR DAO SHIFT: " + exc.getMessage());
    }
  }

  @Override
  public void delete(CompanyUser object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "DELETE FROM company_user WHERE (company_user_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setLong(1, object.getId());
      query.execute();
      connection.commit();
      connection.endRequest();
      query.close();
      connection.close();
    } catch (SQLException exc) {
      System.err.println("ERROR DAO DELETE: " + exc.getMessage());
    }
  }

  @Override
  public CompanyUser findById(long id) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    CompanyUser companyUser = null;
    try {
      sentence = "SELECT * FROM company_user WHERE (company_user_id = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setLong(1, id);
      result = query.executeQuery();
      companyUser = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return companyUser;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public CompanyUser findByCode(String code) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    CompanyUser companyUser = null;
    try {
      sentence = "SELECT * FROM company_user WHERE (company_user_code = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      result = query.executeQuery();
      companyUser = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return companyUser;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public List<CompanyUser> findAll() {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet results = null;
    List<CompanyUser> list = null;
    try {
      sentence = "SELECT * FROM company_user";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      results = query.executeQuery();
      list = this.returnListObjects(results);
      connection.commit();
      connection.endRequest();
      results.close();
      query.close();
      connection.close();
      return list;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }
}
