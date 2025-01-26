package io.scout.dao;

import io.scout.persistence.DBPersistence;
import io.scout.model.SystemTag;
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
public class SystemTagDAOImp implements IBasicDAO<SystemTag> {

  public SystemTagDAOImp() {}

  private SystemTag returnDefaultObject() {
    SystemTag object = new SystemTag();
    object.setId((short) 0);
    object.setCode(null);
    object.setFlagState(false);
    return object;
  }

  private List<SystemTag> returnListObjects(ResultSet result) {
    SystemTag item = null;
    List<SystemTag> list = null;
    try {
      list = new ArrayList<SystemTag>();
      while (result.next()) {
        item = new SystemTag();
        item.setId(result.getShort("system_tag_id"));
        item.setCode(result.getString("system_tag_code"));
        item.setName(result.getString("name"));
        item.setSerialNumber(result.getString("serial_number"));
        item.setFlagState(result.getBoolean("flag_state"));
        item.setRegisterDate(result.getDate("register_date"));
        list.add(item);
      }
      return list;
    } catch (SQLException exc) {
      item = this.returnDefaultObject();
      list = new ArrayList<SystemTag>();
      list.add(item);
      return list;
    }
  }

  @Override
  public int save(SystemTag object) {
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
      sentence = "INSERT INTO system_tag(name, serial_number, flag_state) VALUES (?, ?, TRUE)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence, Statement.RETURN_GENERATED_KEYS);
      query.setString(1, object.getName());
      query.setString(2, object.getSerialNumber());
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
      sentence = "UPDATE system_tag SET system_tag_code = ? WHERE (flag_state = TRUE) AND (system_tag_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      query.setShort(2, (short) id);
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
  public void update(SystemTag object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE system_tag SET name = ?, serial_number = ? WHERE (flag_state = TRUE) AND (system_tag_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, object.getName());
      query.setString(2, object.getSerialNumber());
      query.setShort(3, object.getId());
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
  public void shift(SystemTag object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE system_tag SET flag_state = ? WHERE (system_tag_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setBoolean(1, object.isFlagState());
      query.setShort(2, object.getId());
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
  public void delete(SystemTag object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "DELETE FROM system_tag WHERE (system_tag_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setShort(1, object.getId());
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
  public SystemTag findById(long id) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    SystemTag systemTag = null;
    try {
      sentence = "SELECT * FROM system_tag WHERE (system_tag_id = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setShort(1, (short) id);
      result = query.executeQuery();
      systemTag = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return systemTag;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public SystemTag findByCode(String code) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    SystemTag systemTag = null;
    try {
      sentence = "SELECT * FROM system_tag WHERE (system_tag_code = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      result = query.executeQuery();
      systemTag = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return systemTag;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public List<SystemTag> findAll() {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    List<SystemTag> list = null;
    try {
      sentence = "SELECT * FROM system_tag";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      result = query.executeQuery();
      list = this.returnListObjects(result);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return list;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }
}
