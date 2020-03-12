package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitNewContactCreation() { click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillNewContactForm(NewContactData newContactData, boolean creation ) {
    type(By.name("firstname"), newContactData.getName());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("mobile"), newContactData.getMobile());
    type(By.name("home"), newContactData.getHome());
    type(By.name("work"), newContactData.getWork());
    type(By.name("email"), newContactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactbyId(int id)  {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {click(By.xpath("//input[@value='Delete']")); }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }


  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//tr[.//*[@id='" + id + "']]//*[@title='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(NewContactData contact, boolean creation) {
    fillNewContactForm(contact, true);
    submitNewContactCreation();
    returnToHomePage();

  }

  public void modify(NewContactData contact) {
    initContactModificationById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(NewContactData contact) {
    selectContactbyId(contact.getId());
    deleteSelectedContact();
    closeAlert();
    returnToHomePage();
  }

  public boolean isThereAContact() {
   return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement element : rows ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String name = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new NewContactData().withId(id).withLastname(lastname).withEmail(null).withName(name).withGroup(null)
              .withAllPhones(allPhones));
    }
    return contacts;
  }

  public NewContactData infoFromEditForm(NewContactData contact) {
    initContactModificationById(contact.getId());
    String lastname = wd.findElement(By.name("firstname")).getAttribute("value");
    String name = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new NewContactData().withId(contact.getId()).withLastname(lastname).withName(name)
            .withTelHome(home).withMobile(mobile).withTelWork(work);
  }

}
