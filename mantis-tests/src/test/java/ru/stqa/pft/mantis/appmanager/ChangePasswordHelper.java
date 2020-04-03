package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper (ApplicationManager app) {
    super(app);
  }

  public void selectUserByName(String name) {
    wd.findElement(By.linkText(name)).click();
  }

  public void resetPassword() {
    wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("span.bigger-110"));
  }
}
