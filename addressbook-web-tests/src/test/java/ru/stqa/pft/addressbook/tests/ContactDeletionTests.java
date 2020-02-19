package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnToHomePage();
  }


}
