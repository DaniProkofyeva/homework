package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    NewContactData modifiedContact = before.iterator().next();
    NewContactData contact = new NewContactData()
            .withId(modifiedContact.getId()).withLastname("Leonova").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
