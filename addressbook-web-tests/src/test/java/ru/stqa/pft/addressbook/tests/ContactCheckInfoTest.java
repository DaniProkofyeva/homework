package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactCheckInfoTest extends TestBase {


  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    NewContactData contact = app.contact().all().iterator().next();
    NewContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergeAddress(NewContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactCheckInfoTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(NewContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactCheckInfoTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(NewContactData contact) {
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactCheckInfoTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
