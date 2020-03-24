package ru.stqa.pft.addressbook.tests;

import jdk.nashorn.internal.codegen.Compiler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    NewContactData modifiedContact = before.iterator().next();
    File photo = new File ("src/test/resources/stru.png");
    NewContactData contact = new NewContactData()
            .withId(modifiedContact.getId()).withLastname("Leonova").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactsListUI();
  }

}
