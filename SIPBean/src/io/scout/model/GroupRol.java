package io.scout.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DEV Scout
 */
public class GroupRol {

  private short id;
  private String code;
  private String name;
  private boolean flagState;
  private Date registerDate;
  private SystemTag systemTag;
  private List<Menu> listMenus;

  public GroupRol() {
    this.listMenus = new ArrayList<Menu>();
  }

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public SystemTag getSystemTag() {
    return systemTag;
  }

  public void setSystemTag(SystemTag systemTag) {
    this.systemTag = systemTag;
  }

  public List<Menu> getListMenus() {
    return listMenus;
  }

  public void setListMenus(List<Menu> listMenus) {
    this.listMenus = listMenus;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + this.id;
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
    final GroupRol other = (GroupRol) obj;
    return this.id == other.id;
  }

  @Override
  public String toString() {
    return "GroupRol {}";
  }
}
