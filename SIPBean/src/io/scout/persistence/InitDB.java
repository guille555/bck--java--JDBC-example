package io.scout.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author DEV Scout
 */
public class InitDB {

  private List<String> getQueriesTables() {
    String queryTableSystemTag = "CREATE TABLE system_tag(system_tag_id SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),system_tag_code VARCHAR(16),name VARCHAR(64),serial_number VARCHAR(8),flag_state BOOLEAN NOT NULL DEFAULT FALSE,register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,CONSTRAINT pk_system_tag_id PRIMARY KEY(system_tag_id),CONSTRAINT uk_system_tag_code UNIQUE(system_tag_code))";
    String queryTableGroupRol = "CREATE TABLE group_rol(group_rol_id SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),group_rol_code VARCHAR(16),name VARCHAR(64),flag_state BOOLEAN NOT NULL DEFAULT FALSE,register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,system_tag_id SMALLINT NOT NULL,CONSTRAINT pk_group_rol_id PRIMARY KEY(group_rol_id),CONSTRAINT uk_group_rol_code UNIQUE(group_rol_code),CONSTRAINT fk_group_rol_system_tag FOREIGN KEY(system_tag_id) REFERENCES system_tag(system_tag_id))";
    String queryTableMenu = "CREATE TABLE menu(menu_id SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),menu_code VARCHAR(16),name VARCHAR(32),flag_state BOOLEAN NOT NULL DEFAULT FALSE,register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,group_rol_id SMALLINT NOT NULL,CONSTRAINT pk_menu_id PRIMARY KEY(menu_id),CONSTRAINT uk_menu_code UNIQUE(menu_code),CONSTRAINT fk_menu_group_rol FOREIGN KEY(group_rol_id) REFERENCES group_rol(group_rol_id))";
    String queryTableCompanyUser = "CREATE TABLE company_user(company_user_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),company_user_code VARCHAR(32),username VARCHAR(8),password VARCHAR(255),flag_state BOOLEAN NOT NULL DEFAULT FALSE,register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,system_tag_id SMALLINT NOT NULL,group_rol_id SMALLINT NOT NULL,CONSTRAINT pk_company_user_id PRIMARY KEY(company_user_id),CONSTRAINT uk_company_user_code UNIQUE(company_user_code),CONSTRAINT fk_company_user_system_tag FOREIGN KEY(system_tag_id) REFERENCES system_tag(system_tag_id),CONSTRAINT fk_company_user_group_role FOREIGN KEY(group_rol_id) REFERENCES group_rol(group_rol_id))";
    List<String> queries = new ArrayList<String>();
    Collections.addAll(queries,
      queryTableSystemTag,
      queryTableGroupRol,
      queryTableMenu,
      queryTableCompanyUser
    );
    return queries;
  }

  public void createTables() {
    Connection connection = null;
    PreparedStatement query = null;
    DBPersistence dbPersistence = null;
    List<String> queries = null;
    try {
      dbPersistence = new DBPersistence();
      queries = this.getQueriesTables();
      connection = dbPersistence.getConnection();
      connection.beginRequest();
      for (String sentence : queries) {
        query = connection.prepareStatement(sentence);
        query.execute();
        query.close();
      }
      connection.commit();
      connection.endRequest();
      connection.close();
    } catch (SQLException exc) {
      System.err.println("ERROR INIT SQL: " + exc.getMessage());
    }
  }
}
