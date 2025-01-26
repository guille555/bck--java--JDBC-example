package io.scout.model;

import java.util.Date;

/**
 * @author DEV Scout
 */
public class CompanyUser {

  private long id;
  private String code;
  private String username;
  private String password;
  private boolean flagState;
  private Date registerDate;
  private SystemTag systemTag;
  private GroupRol groupRol;

  public CompanyUser() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public GroupRol getGroupRol() {
    return groupRol;
  }

  public void setGroupRol(GroupRol groupRol) {
    this.groupRol = groupRol;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
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
    final CompanyUser other = (CompanyUser) obj;
    return this.id == other.id;
  }

  @Override
  public String toString() {
    return "CompanyUser {}";
  }
}
