package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new NewContactData().withLastname("Prokofyeva").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    NewContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
