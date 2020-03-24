package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;
import ru.stqa.pft.addressbook.tests.NewContactCreationTests;

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
    attach(By.name("photo"), newContactData.getPhoto());
    type(By.name("mobile"), newContactData.getMobile());
    type(By.name("home"), newContactData.getHome());
    type(By.name("work"), newContactData.getWork());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("email2"), newContactData.getEmail2());
    type(By.name("email3"), newContactData.getEmail3());
    type(By.name("address"), newContactData.getAddress());

    if (creation) {
      if (newContactData.getGroups().size() > 0) {
        Assert.assertTrue(newContactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroups().iterator().next().getName());
      }
    }else {
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

  public void clickOnGroup() {
    wd.findElement(By.name("group")).click();
  }

  public void selectGroup(NewContactData newContactData, boolean selection) {
    if (selection) {
      if (newContactData.getGroups().size() > 0) {
        Assert.assertTrue(newContactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(newContactData.getGroups().iterator().next().getName());
      }
    }
  }

  public void addToGroup() {
    wd.findElement(By.name("add")).click();
  }

  public void addContactToGroup(NewContactData contact) {
    selectContactbyId(contact.getId());
    clickOnGroup();
    selectGroup(contact, true);
    addToGroup();
    returnToHomePage();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(NewContactData contact, boolean creation) {
    fillNewContactForm(contact, true);
    submitNewContactCreation();
    contactCache = null;
    returnToHomePage();

  }

  public void modify(NewContactData contact) {
    initContactModificationById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(NewContactData contact) {
    selectContactbyId(contact.getId());
    deleteSelectedContact();
    closeAlert();
    contactCache = null;
    returnToHomePage();
  }
  private Contacts contactCache = null;

  public boolean isThereAContact() {
   return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int count() {
   return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement element : rows ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String name = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String Address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new NewContactData().withId(id).withLastname(lastname).withEmail(null).withName(name)
              .withAllPhones(allPhones).withAddress(Address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public NewContactData infoFromEditForm(NewContactData contact) {
    initContactModificationById(contact.getId());
    String lastname = wd.findElement(By.name("firstname")).getAttribute("value");
    String name = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new NewContactData().withId(contact.getId()).withLastname(lastname).withName(name)
            .withTelHome(home).withMobile(mobile).withTelWork(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

}
