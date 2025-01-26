package io.scout.dao;

import io.scout.model.Menu;
import io.scout.persistence.DBPersistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author DEV Scout
 */
public class MenuDAOImp implements IBasicDAO<Menu> {

  public MenuDAOImp() {}

  private Menu returnDefaultObject() {
    Menu object = new Menu();
    object.setId((short) 0);
    object.setCode(null);
    object.setFlagState(false);
    return object;
  }

  private List<Menu> returnListObjects(ResultSet results) {
    Menu item = null;
    List<Menu> list = null;
    try {
      list = new ArrayList<Menu>();
      while (results.next()) {
        item = new Menu();
        item.setId(results.getShort("menu_id"));
        item.setCode(results.getString("menu_code"));
        item.setName(results.getString("name"));
        item.setFlagState(results.getBoolean("flag_state"));
        item.setRegisterDate(results.getDate("register_date"));
        item.setGroupRol(null);
        list.add(item);
      }
      return list;
    } catch (SQLException exc) {
      item = this.returnDefaultObject();
      list = new ArrayList<Menu>();
      list.add(item);
      return list;
    }
  }

  @Override
  public int save(Menu object) {
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
      sentence = "INSERT INTO menu(name, flag_state, group_rol_id) VALUES (?, TRUE, ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence, Statement.RETURN_GENERATED_KEYS);
      query.setString(1, object.getName());
      query.setShort(2, object.getGroupRol().getId());
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
      sentence = "UPDATE menu SET menu_code = ? WHERE (flag_state = TRUE) AND (menu_id = ?)";
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
  public void update(Menu object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE menu SET name = ?, group_rol_id = ? WHERE (flag_state = TRUE) AND (menu_id = ?)";
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, object.getName());
      query.setShort(2, object.getGroupRol().getId());
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
  public void shift(Menu object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "UPDATE menu SET flag_state = ? WHERE (menu_id = ?)";
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
  public void delete(Menu object) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    try {
      dbPersistence = new DBPersistence();
      sentence = "DELETE FROM menu WHERE (menu_id = ?)";
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
  public Menu findById(long id) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    Menu object = null;
    try {
      sentence = "SELECT * FROM menu WHERE (menu_id = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setShort(1, (short) id);
      result = query.executeQuery();
      object = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return object;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public Menu findByCode(String code) {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet result = null;
    Menu object = null;
    try {
      sentence = "SELECT * FROM menu WHERE (menu_code = ?) FETCH FIRST 1 ROWS ONLY";
      dbPersistence = new DBPersistence();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      query = connection.prepareStatement(sentence);
      query.setString(1, code);
      result = query.executeQuery();
      object = this.returnListObjects(result).get(0);
      connection.commit();
      connection.endRequest();
      result.close();
      query.close();
      connection.close();
      return object;
    } catch (SQLException exc) {
      System.err.println("ERROR DAO FETCHING: " + exc.getMessage());
      throw new EmptyStackException();
    }
  }

  @Override
  public List<Menu> findAll() {
    String sentence = null;
    DBPersistence dbPersistence = null;
    Connection connection = null;
    PreparedStatement query = null;
    ResultSet results = null;
    List<Menu> list = null;
    try {
      sentence = "SELECT * FROM menu";
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
