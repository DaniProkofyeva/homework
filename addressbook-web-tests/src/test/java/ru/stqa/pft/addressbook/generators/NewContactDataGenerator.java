package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.NewContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

public class NewContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count" )
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args) throws IOException {
    NewContactDataGenerator generator = new NewContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }
  private void run() throws IOException {
    List<NewContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File (file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File (file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }
  private void saveAsXml(List<NewContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(NewContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }
  private void saveAsCsv(List<NewContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (NewContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getLastname(), contact.getName(), contact.getHome(), contact.getMobile(), contact.getWork(), contact.getAddress(),contact.getEmail(), contact.getEmail2(), contact.getEmail3(),contact.getPhoto()));
      }
    }
  }

  private List<NewContactData> generateContacts(int count) {
    List<NewContactData> contacts = new ArrayList<NewContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new NewContactData().withLastname(String.format("lastname %s", i))
              .withName(String.format("name %s", i))
              .withAddress(String.format("address %s", i)).withMobile(String.format("mobile %s", i))
              .withTelHome(String.format("home %s", i)).withTelWork(String.format("work %s", i))
              .withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i))
              .withEmail3(String.format("email3 %s", i)).withPhoto(new File("src/test/resources/stru.png")));
    }
    return contacts;
  }
}