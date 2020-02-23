package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

  public void selectContact() {click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input")); }

  public void deleteSelectedContact() {click(By.xpath("//input[@value='Delete']")); }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
