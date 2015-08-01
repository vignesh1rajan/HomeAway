package UI;

import UI.AbstractPageObject.HeadPageBase;
import UI.AbstractPageObject.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by vrajan on 7/29/2015.
 */
public class HomePage extends HeadPageBase {
    @FindBy(id = "main-nav") private WebElement mainNav;
    @FindBy(id = "post-98") private WebElement iphoneTab;
    @FindBy(className = "product_view_96") private WebElement iphoneBlackList;
    @FindBy(className = "fancy_notification") private WebElement cartDialogue;
    @FindBy(className = "go_to_checkout") private WebElement cartCheckoutButton;

    @FindBy(className = "post-29") private WebElement cartPage;


  /************
   * Constructor
   * @param webDriver - WebDriver used to interact with the page
   * @param wait - The FluentWait on the webDriver with desired wait settings.
   */

  public HomePage(WebDriver webDriver, FluentWait<WebDriver> wait){
    super(webDriver,wait);
    PageFactory.initElements(getWebDriver(), this);
  }

  public HomePage (PageBase pb){
    super(pb);
  }

  public HomePage navigateToHome(){
    getWebDriver().navigate().to("http://store.demoqa.com");
    isWebElementVisible(mainNav);
    return new HomePage(this);
  }


  public boolean navToIphoneListPage(){
    this.selectScreen(productCategory, iphones);

    isWebElementVisible(iphoneTab);
    return true;
  }

  public boolean verifyIphoneBlack(){
    return isWebElementVisible(iphoneBlackList);
  }

  public int getPhonePrice(){
    WebElement priceElement = iphoneBlackList.findElement(By.className("product_price_96"));
    String sPrice = priceElement.getText();
    NumberFormat nf =  NumberFormat.getCurrencyInstance();

    //sPrice = sPrice.replace("$", "");Integer.parseInt(sPrice) ;

    int phonePrice = 0;
    try {
      phonePrice = nf.parse(sPrice).intValue();
    } catch(ParseException e) {
      System.out.println("Unable to parse price format" + e.getMessage());
    }

    return phonePrice;
  }
  public CheckoutPage addIphoneToCart(){

    WebElement iphoneCart = iphoneBlackList.findElement(By.className("wpsc_buy_button"));
    iphoneCart.click();
    isWebElementVisible(cartDialogue);
    isWebElementVisible(cartCheckoutButton);
    cartCheckoutButton.click();
    isWebElementVisible(cartPage);
    return new CheckoutPage(webDriver,wait);
  }

}
