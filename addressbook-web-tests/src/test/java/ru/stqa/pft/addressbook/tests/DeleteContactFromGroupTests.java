package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;
import java.util.Set;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class DeleteContactFromGroupTests extends TestBase {

  private NewContactData contact;
  private GroupData group;
  private boolean contactCreated;
  private boolean groupCreated;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria"), true);
      app.goTo().homePage();
      contact = app.db().contacts().iterator().next();
      contactCreated = true;
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      app.goTo().homePage();
      group = app.db().groups().iterator().next();
      groupCreated = true;
    }
    if (!(contactCreated && groupCreated)) {
      for (NewContactData c : app.db().contacts()) {
        if (c.getGroups().size() != 0) {
          contact = c;
          group = c.getGroups().iterator().next();
          return;
        }
      }
    }
      contact = app.db().contacts().iterator().next();
      group = app.db().groups().iterator().next();
      app.goTo().homePage();
      app.contact().addInSelectGroup(contact.getId(), group.getName());
      app.goTo().homePage();
  }

  @Test
  public void testDeleteContactFromGroup() {
    NewContactData contactWithoutGroup = contact.removeOfGroup(group);
    app.goTo().homePage();
    app.contact().deleteFromSelectedGroup(contact.getId(), group.getName());
    assertThat(contact.getGroups(), equalTo(contactWithoutGroup.getGroups().without(group)));
  }
}


