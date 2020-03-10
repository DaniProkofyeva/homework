package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


  @Test (enabled = false)
  public void testContactDeletion() {
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoNewContactPage();
      app.getContactHelper().createContact(new NewContactData("Prokofyeva", "81234567890", "daria@mail.com", "Daria", "test1"), true);
    }
    List<NewContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnToHomePage();
    List<NewContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
