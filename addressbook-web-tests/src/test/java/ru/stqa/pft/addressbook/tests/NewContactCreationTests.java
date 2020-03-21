package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.DataProvider;
import java.util.Iterator;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class NewContactCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(NewContactData.class);
      List<NewContactData> contacts = (List<NewContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromXml")
  public void testNewContactCreation(NewContactData contact) {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    app.contact().create(contact,true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadNewContactCreation() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    NewContactData contact = new NewContactData().withLastname("Prokofyeva'").withMobile("81234567890").withEmail("daria@mail.com").withName("Daria").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
  }

}
