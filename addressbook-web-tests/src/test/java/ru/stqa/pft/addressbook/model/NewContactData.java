package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NewContactData {
  private int id;
  private final String lastname;
  private final String mobile;
  private final String email;
  private final String name;
  private String group;

  public NewContactData(int id,String lastname, String mobile, String email, String name, String group) {
    this.id = id;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.name = name;
    this.group = group;
  }

  public NewContactData(String lastname, String mobile, String email, String name, String group) {
    this.id = Integer.MAX_VALUE;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.name = name;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContactData that = (NewContactData) o;
    return Objects.equals(lastname, that.lastname) &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, name);
  }

  @Override
  public String toString() {
    return "NewContactData{" +
            "id='" + id + '\'' +
            ", lastname='" + lastname + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}
