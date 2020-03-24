package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.NewContactData;

public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria"), true);
      app.goTo().homePage();
      NewContactData contact = app.db().contacts().iterator().next();
      app.contact().addContactToGroup(contact);
    }
    NewContactData contact = app.db().contacts().iterator().next();
    if (contact.getGroups().size() == 0)
      app.contact().addContactToGroup(contact);
  }
}
