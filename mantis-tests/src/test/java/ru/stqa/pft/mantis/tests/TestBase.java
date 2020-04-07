package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import static org.testng.Assert.assertEquals;
import org.testng.SkipException;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
    app.stop();
  }
  boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    String login = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    MantisConnectPortType mc = app.soap().getMantisConnect();
    IssueData issueIdTest = mc.mc_issue_get(login, password, BigInteger.valueOf(issueId));
    if (issueIdTest.getResolution().getName().equals("fixed")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


