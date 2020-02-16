package ru.stqa.pft.addressbook;

public class NewContactData {
  private final String lastname;
  private final String mobile;
  private final String email;
  private final String name;

  public NewContactData(String lastname, String mobile, String email, String name) {
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.name = name;
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
}
