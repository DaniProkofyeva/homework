package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Iterator;

public class AddContactToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void addContactToGroup(){
    app.goTo().homePage();
    NewContactData contactsData = app.db().contacts().iterator().next();
    app.contact().addContactToGroup(contactsData);
    NewContactData after = null;
    Iterator<NewContactData> allContacts = app.db().contacts().iterator();
    while (allContacts.hasNext()) {
      after = allContacts.next();
      if (after.getId() == contactsData.getId()) {
        break;
      }
    }
    Assert.assertEquals(contactsData.getGroups().size()+1, after.getGroups().size());
  }

}