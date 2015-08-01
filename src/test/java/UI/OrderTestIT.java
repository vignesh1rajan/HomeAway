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
  CheckoutPage checkoutPage = null;

  int phonePrice;
  /*** checkout info ***/
  String fName = "Bigfish";
  String lName = "Bigfoot";
  String email = "test@gmail.com";
  String address = "1600 Pensylvania Ave";
  String city = "Conway";
  String country = "USA";
  String pcode = "72034";
  String phone = "555-666-7798";

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
    Assert.assertTrue(homePage.navToIphoneListPage()," Can not select Iphone List page from dropdown menu");
    phonePrice = homePage.getPhonePrice();
    Assert.assertTrue(homePage.verifyIphoneBlack(), "Iphone Black is not listed in Iphone Page ");
  }

  @Test(description = "03) Order Iphone ", dependsOnMethods = "navIphoneList", groups = "SmokeTest")
  public void addIphoneToCart(){
    checkoutPage = homePage.addIphoneToCart();
  }

  @Test(description = "04) Checkout page", dependsOnMethods = "addIphoneToCart", groups = "SmokeTest")
  public void checkoutCart(){
  Assert.assertTrue(checkoutPage.checkoutIphone(), "Unable add Iphone to cart");
    checkoutPage.checkoutShipping();
    checkoutPage.verifyTotalCost(phonePrice);
    checkoutPage.addCheckoutInfo(email,fName,lName,address,city,pcode,country,phone);
  }
}
