package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;

public class NewContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testNewContactCreation() {
    app.goTo().gotoHomePage();
    List<NewContactData> before = app.getContactHelper().getContactList();
    app.goTo().gotoNewContactPage();
    NewContactData contact = new NewContactData("Prokofyeva", "81234567890", "daria@mail.com", "Daria", "test1");
    app.getContactHelper().createContact(contact,true);
    app.goTo().gotoHomePage();
    List<NewContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

   before.add(contact);
   Comparator<? super NewContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
   before.sort(byId);
   after.sort(byId);
   Assert.assertEquals(before, after);
  }

}
