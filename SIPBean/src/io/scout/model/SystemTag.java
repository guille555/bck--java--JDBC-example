package io.scout.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DEV Scout
 */
public class SystemTag {

  private short id;
  private String code;
  private String name;
  private String serialNumber;
  private boolean flagState;
  private Date registerDate;
  private List<CompanyUser> listCompanyUsers;
  private List<GroupRol> listGroupRoles;

  public SystemTag() {
    this.listCompanyUsers = new ArrayList<CompanyUser>();
    this.listGroupRoles = new ArrayList<GroupRol>();
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

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
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

  public List<CompanyUser> getListCompanyUsers() {
    return listCompanyUsers;
  }

  public void setListCompanyUsers(List<CompanyUser> listCompanyUsers) {
    this.listCompanyUsers = listCompanyUsers;
  }

  public List<GroupRol> getListGroupRoles() {
    return listGroupRoles;
  }

  public void setListGroupRoles(List<GroupRol> listGroupRoles) {
    this.listGroupRoles = listGroupRoles;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + this.id;
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
    final SystemTag other = (SystemTag) obj;
    return this.id == other.id;
  }

  @Override
  public String toString() {
    return "SystemTag {}";
  }
}
