package ru.stqa.pft.addressbook.model;

public class NewContactData {
  private final String lastname;
  private final String mobile;
  private final String email;
  private final String name;
  private String group;

  public NewContactData(String lastname, String mobile, String email, String name, String group) {
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.name = name;
    this.group = group;
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
}
