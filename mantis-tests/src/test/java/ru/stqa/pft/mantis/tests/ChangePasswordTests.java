package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


  public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
      app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
      String newPassword = "password12345678";
      app.goTo().login("administrator", "root");
      app.goTo().manageUsersPage();
      Users usersList = app.db().users();
      UserData selectedUser = usersList.iterator().next();
      String username = selectedUser.getUsername();
      app.admin().selectUserByName(username);
      app.admin().resetPassword();
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String confirmationLink = app.registration().findConfirmationLink(mailMessages, selectedUser.getEmail());
      app.admin().changePassword(confirmationLink, newPassword);
      assertTrue(app.newSession().login(username, newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
      app.mail().stop();
    }
}
