package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Id;

  public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public int getId() {
      return id;
    }

    public UserData withId(int id) {
      this.id = id;
      return this;
    }

    public String getUsername() {
      return username;
    }

    public UserData withUsername(String username) {
      this.username = username;
      return this;
    }

    public String getEmail() {
      return email;
    }

    public UserData withEmail(String email) {
      this.email = email;
      return this;
    }

    public String getPassword() {
      return password;
    }

    public UserData withPassword(String password) {
      this.password = password;
      return this;
    }

    @Override
    public String toString() {
      return "UserData{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              '}';
    }
  }
