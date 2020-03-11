package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NewContactData {
  private int id = Integer.MAX_VALUE;
  private String lastname;
  private String mobile;
  private String email;
  private String name;
  private String group;

  public int getId() {
    return id;
  }

  public NewContactData withId(int id) {
    this.id = id;
    return this;
  }

  public NewContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public NewContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public NewContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public NewContactData withName(String name) {
    this.name = name;
    return this;
  }

  public NewContactData withGroup(String group) {
    this.group = group;
    return this;
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
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, name);
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
