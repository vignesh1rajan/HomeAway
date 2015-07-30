package UI;


import UI.AbstractPageObject.HeadPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vrajan on 7/29/2015.
 */
public class OrderTestIT extends TestBase {

  HomePage homePage = null;

  /********************
   * Overridden methods
   *******************/

  @Override
  public HeadPageBase instantiateHeadObject(WebDriver webDriver,FluentWait<WebDriver> wait) {
    return new HomePage(webDriver, wait);
  }

  @Test(description = "01) Navigate to main page", groups = "SmokeTest")
  public void mainPage(){
    homePage =(HomePage) pageBase;
    homePage.navigateToHome();
  }

  @Test(description = "02) Navigate to Iphone List page",dependsOnMethods = "mainPage",groups = "SmokeTest")
  public void navIphoneList(){
    Assert.assertTrue(homePage.navToIphoneListPage()," Can not select Iphone List page from dropdown menu");
  }



}
