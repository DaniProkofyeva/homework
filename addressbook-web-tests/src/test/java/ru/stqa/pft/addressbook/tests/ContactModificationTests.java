package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new NewContactData("Prokofyeva", "81234567890", "daria@mail.com", "Daria"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
