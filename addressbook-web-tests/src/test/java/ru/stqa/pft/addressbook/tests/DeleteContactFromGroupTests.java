package ru.stqa.pft.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria"), true);
      app.goTo().homePage();
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
      app.goTo().homePage();
    }
    if (app.db().contacts().iterator().next().getGroups().size() == 0) {
      app.goTo().homePage();
      Contacts contacts = app.db().contacts();
      Groups groups = app.db().groups();
      app.contact().addContactToGroup(contacts.iterator().next(), groups.iterator().next());
    }
  }


  @Test
  public void testDeleteContactFromGroupTests() throws InterruptedException {
    Groups list = app.db().groups();
    ContactsInGroup before = app.db().groupsWithContact();
    ContactsInGroupData toDelete = before.iterator().next();
    app.contact().removeFromGroup(list, toDelete);
    ContactsInGroup after = app.db().groupsWithContact();
    assertThat(after, equalTo(before.withoutAdded(toDelete).withAdded(toDelete)));
    System.out.println("before: " + before.withoutAdded(toDelete).withAdded(toDelete));
    System.out.println("after: " + after);
  }
}

