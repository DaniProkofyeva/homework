package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;


  public class ChangePasswordTests extends TestBase {

    @Test
    public void testChangePassword() throws IOException {
      app.goTo().login("administrator", "root");
      app.goTo().manageUsersPage();
      Users usersList = app.db().users();
      UserData selectedUser = usersList.iterator().next();
      String username = selectedUser.getUsername();
      app.user().selectUserByName(username);
    }

}
