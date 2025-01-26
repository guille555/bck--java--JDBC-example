package io.scout.dao;

import io.scout.model.GroupRol;
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
public class GroupRolDAOImp implements IBasicDAO<GroupRol> {

  public GroupRolDAOImp() {}

  private GroupRol returnDefaultObject() {
    GroupRol object = new GroupRol();
    object.setId((short) 0);
    object.setCode(null);
    object.setFlagState(false);
    return object;
  }

  private List<GroupRol> returnListObjects(ResultSet results) {
    GroupRol item = null;
    List<GroupRol> list = null;
    try {
      list = new ArrayList<GroupRol>();
      while (results.next()) {
        item = new GroupRol();
        item.setId(results.getShort("group_rol_id"));
        item.setCode(results.getString("group_rol_code"));
        item.setName(results.getString("name"));
        item.setFlagState(results.getBoolean("flag_state"));
        item.setRegisterDate(results.getDate("register_date"));
        item.setSystemTag(null);
        list.add(item);
      }
      return list;
    } catch (SQLException exc) {
      item = this.returnDefaultObject();
      list = new ArrayList<GroupRol>();
      list.add(item);
      return list;
    }
  }

  @Override
  public int save(GroupRol object) {
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
      sentence = "INSERT INTO group_rol(name, flag_state, system_tag_id) VALUES (?, TRUE, ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence, Statement.RETURN_GENERATED_KEYS);
      query.setString(1, object.getName());
      query.setShort(2, object.getSystemTag().getId());
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
      sentence = "UPDATE group_rol SET group_rol_code = ? WHERE (flag_state = TRUE) AND (group_rol_id = ?)";
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
  public void update(GroupRol data) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE group_rol SET name = ?, system_tag_id = ? WHERE (flag_state = TRUE) AND (group_rol_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, data.getName());
      query.setShort(2, data.getSystemTag().getId());
      query.setShort(3, data.getId());
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
  public void shift(GroupRol object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE group_rol SET flag_state = ? WHERE (system_tag_id = ?)";
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
  public void delete(GroupRol object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "DELETE FROM group_rol WHERE (group_rol_id = ?)";
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
  public GroupRol findById(long id) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    GroupRol groupRol = null;
    try {
      sentence = "SELECT * FROM group_rol WHERE (group_rol_id = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setShort(1, (short) id);
      result = query.executeQuery();
      groupRol = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return groupRol;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public GroupRol findByCode(String code) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    GroupRol groupRol = null;
    try {
      sentence = "SELECT * FROM group_rol WHERE (group_rol_code = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      result = query.executeQuery();
      groupRol = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return groupRol;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public List<GroupRol> findAll() {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet results = null;
    List<GroupRol> list = null;
    try {
      sentence = "SELECT * FROM group_rol";
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
