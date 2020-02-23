package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactData;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().createContact(new NewContactData("Prokofyeva", "81234567890", "daria@mail.com", "Daria", "test1"), true);
  }

}
