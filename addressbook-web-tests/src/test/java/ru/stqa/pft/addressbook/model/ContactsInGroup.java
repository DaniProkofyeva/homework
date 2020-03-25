package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroup extends ForwardingSet<ContactsInGroupData> {
  private Set<ContactsInGroupData> delegate;

  public ContactsInGroup(Collection<ContactsInGroupData> groups) {
    this.delegate = new HashSet<>(groups);
  }

  @Override
  protected Set<ContactsInGroupData> delegate() {
    return delegate;
  }

  public ContactsInGroup withAdded(ContactsInGroupData group) {
    ContactsInGroup groups = new ContactsInGroup(this);
    groups.add(group);
    return groups;
  }

  public ContactsInGroup without(ContactsInGroupData group) {
    ContactsInGroup groups = new ContactsInGroup(this);
    groups.remove(group);
    return groups;
  }
}

