package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroupTests extends TestBase {

  NewContactData contact;
  GroupData group;
  private boolean groupCreated;
  private boolean contactCreated;


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria"), true);
      app.goTo().homePage();
      contactCreated = true;
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
      app.goTo().homePage();
      groupCreated = true;
    }
    if (!(contactCreated && groupCreated)) {
      for (GroupData g : app.db().groups()) {
        for (NewContactData c : app.db().contacts()) {
          if (!c.getGroups().contains(g)) {
            contact = c;
            group = g;
            return;
          }
        }
      }
      contact = app.db().contacts().iterator().next();
      app.group().create(new GroupData().withName("test 1"));
      app.goTo().groupPage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts before = app.db().contacts();
    NewContactData contactWithAddedGroup = contact.inGroup(group);
    app.goTo().homePage();
    app.contact().addInSelectGroup(contact.getId(), group.getName());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contactWithAddedGroup)));
  }
}