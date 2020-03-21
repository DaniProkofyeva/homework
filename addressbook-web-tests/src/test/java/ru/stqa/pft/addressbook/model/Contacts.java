package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<NewContactData> {

  private Set<NewContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<NewContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  public Contacts(Collection<NewContactData> contacts) {
    this.delegate = new HashSet<NewContactData>(contacts);
  }

  @Override
  protected Set<NewContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(NewContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(NewContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
