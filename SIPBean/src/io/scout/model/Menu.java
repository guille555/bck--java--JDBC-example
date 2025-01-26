package io.scout.model;

import java.util.Date;

/**
 * @author DEV Scout
 */
public class Menu {

  private short id;
  private String code;
  private String name;
  private boolean flagState;
  private Date registerDate;
  private GroupRol groupRol;

  public Menu() {}

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isFlagState() {
    return flagState;
  }

  public void setFlagState(boolean flagState) {
    this.flagState = flagState;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }

  public GroupRol getGroupRol() {
    return groupRol;
  }

  public void setGroupRol(GroupRol groupRol) {
    this.groupRol = groupRol;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + this.id;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Menu other = (Menu) obj;
    return this.id == other.id;
  }

  @Override
  public String toString() {
    return "Menu {}";
  }
}
