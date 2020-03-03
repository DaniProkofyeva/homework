package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.ArrayList;
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
    type(By.name("email"), newContactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact(int index)  {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {click(By.xpath("//input[@value='Delete']")); }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(NewContactData contact, boolean creation) {
    fillNewContactForm(contact, true);
    submitNewContactCreation();
    returnToHomePage();

  }

  public boolean isThereAContact() {
   return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<NewContactData> getContactList() {
    List<NewContactData> contacts = new ArrayList<NewContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement element : rows ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String name = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      NewContactData contact = new NewContactData(id, lastname, null, null, name, null);
      contacts.add(contact);
    }
    return contacts;
  }



}
