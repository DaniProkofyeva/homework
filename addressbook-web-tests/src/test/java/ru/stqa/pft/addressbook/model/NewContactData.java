package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import net.bytebuddy.build.Plugin;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class NewContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "firstname")
    private String name;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String telHome;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String telWork;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column (name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column (name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Transient
  private File photo;


  public File getPhoto() {
    return photo;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  public NewContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public NewContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
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

  public NewContactData withTelHome(String home) {
    this.telHome = home;
    return this;
  }

  public NewContactData withTelWork(String work) {
    this.telWork = work;
    return this;
  }

  public NewContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public NewContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public NewContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public NewContactData withName(String name) {
    this.name = name;
    return this;
  }

  public NewContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public NewContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getHome() {
    return telHome;
  }

  public String getWork() {
    return telWork;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public NewContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  @Override
  public String toString() {
    return "NewContactData{" +
            "id='" + id + '\'' +
            ", lastname='" + lastname + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContactData that = (NewContactData) o;
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(name, that.name) &&
            Objects.equals(address, that.address) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(telHome, that.telHome) &&
            Objects.equals(telWork, that.telWork) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, name, address, mobile, telHome, telWork, email, email2, email3);
  }
}
