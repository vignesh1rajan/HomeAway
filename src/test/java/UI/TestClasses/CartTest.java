package UI.TestClasses;

import UI.AbstractPageObject.HeadPageBase;
import UI.Pages.CheckoutPage;
import UI.Pages.HomePage;
import UI.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vrajan on 8/2/2015.
 */
public class CartTest extends TestBase {

  HomePage homePage = null;
  CheckoutPage checkoutPage = null;

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

  @Test(description = "02) Navigate to Iphone List page, Select Iphone Black",dependsOnMethods = "mainPage",groups = "SmokeTest")
  public void navIphoneList(){
    Assert.assertTrue(homePage.navToIphoneListPage(), " Can not select Iphone List page from dropdown menu");
    Assert.assertTrue(homePage.verifyIphoneBlack(), "Iphone Black is not listed in Iphone Page ");
  }

  @Test(description = "03) Order Iphone ", dependsOnMethods = "navIphoneList", groups = "SmokeTest")
  public void addIphoneToCart(){
    checkoutPage = homePage.addIphoneToCart();
    Assert.assertEquals(checkoutPage.removeFromCart(), "Oops, there is nothing in your cart.", "Empty Cart did not return message");
  }
}
