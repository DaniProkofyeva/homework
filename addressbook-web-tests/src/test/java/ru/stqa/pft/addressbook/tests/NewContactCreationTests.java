package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    app.goTo().contactPage();
    NewContactData contact = new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1").withPhoto(photo);
    app.contact().create(contact,true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
           before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadNewContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    NewContactData contact = new NewContactData().withLastname("Prokofyeva'").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
