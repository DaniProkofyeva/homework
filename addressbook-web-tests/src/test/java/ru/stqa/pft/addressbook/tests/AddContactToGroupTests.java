package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{

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
  }

  @Test
  public void testAddContactToGroupTests() {
    ContactsInGroup before = app.db().groupsWithContact();
    NewContactData contactAddToGroup = app.db().contacts().iterator().next();
    GroupData groupAddToContact = app.db().groups().iterator().next();
    app.contact().addContactToGroup(contactAddToGroup, groupAddToContact);
    ContactsInGroup after = app.db().groupsWithContact();
    assertThat(after, equalTo(before.withAdded(new ContactsInGroupData().withGroupId(groupAddToContact.getId()))));
  }

}