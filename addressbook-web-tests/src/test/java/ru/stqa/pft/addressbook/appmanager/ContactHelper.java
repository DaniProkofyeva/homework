package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitNewContactCreation() { click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillNewContactForm(NewContactData newContactData) {
    type(By.name("firstname"), newContactData.getName());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("mobile"), newContactData.getMobile());
    type(By.name("email"), newContactData.getEmail());
  }

}
