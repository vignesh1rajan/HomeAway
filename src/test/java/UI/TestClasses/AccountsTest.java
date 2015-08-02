package UI.TestClasses;

import UI.AbstractPageObject.HeadPageBase;
import UI.Pages.AccountsPage;
import UI.Pages.HomePage;
import UI.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vrajan on 8/1/2015.
 */
public class AccountsTest extends TestBase {

  HomePage homePage = null;
  AccountsPage accountsPage = null;


  String address = "Address |" ;
  String loginname = "vrajan";
  String pass = "HK8qHQtxqcxm";

  /********************
   * Overridden methods
   *******************/

  @Override
  public HeadPageBase instantiateHeadObject(WebDriver webDriver,FluentWait<WebDriver> wait) {
    return new HomePage(webDriver, wait);
  }

  @Test(description = "01) Navigate to accounts page", groups = "SmokeTest")
  public void mainPage(){
    homePage =(HomePage) pageBase;
    homePage.navigateToHome();
  }

  @Test(description = "02) Login to account",groups = "SmokeTest",dependsOnMethods = "mainPage")
  public void login(){
    homePage.navToAccountPage();
    accountsPage = homePage.loginToAccount(loginname , pass);
  }

  @Test(description = "03) Edit Account", dependsOnMethods = "login", groups = "SmokeTest")
  public void editAccount(){
    Calendar cal = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    address = address+df.format(cal.getTime());
    Assert.assertTrue(accountsPage.editDetails(address), "Failure when editting account page");
  }

  @Test(description = "04) Logout of account ",dependsOnMethods = "editAccount",groups = "SmokeTest")
  public void logout(){
    Assert.assertTrue(accountsPage.logout(), "Failure when logging out of edit account page");
    homePage = accountsPage.returnPO();
  }

  @Test(description = "05) Verify Account", dependsOnMethods = "logout", groups = "SmokeTest")
  public void verifyAccout(){
    homePage.navigateToHome();
    homePage.navToAccountPage();
    accountsPage = homePage.loginToAccount(loginname, pass);
    accountsPage.verifyDetails(address);
  }
}
