package UI;

import UI.AbstractPageObject.HeadPageBase;
import UI.AbstractPageObject.PageBase;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by vrajan on 7/31/2015.
 */
public class CheckoutPage extends HeadPageBase {

  @FindBy(xpath = "//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[1]") private WebElement quantityText;
  @FindBy(name = "submit") private WebElement updateBtn;
  @FindBy(className = "step2") private WebElement continueBtn;
  @FindBy(id = "wpsc_shopping_cart_container") private WebElement infoContainer;
  @FindBy(xpath = "//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]") private WebElement quantityContainer;
  @FindBy(id = "current_country") private WebElement countryDropdown;
  @FindBy(id = "change_country") private WebElement shippingContainer;
  @FindBy(className = "wpsc_buy_button") private WebElement purchaseBth;
  @FindBy(id = "post-30") private WebElement confirmOrder;

  /*** Cost Table  **/
  @FindBy(className = "total_shipping") private WebElement shippingTable;
  @FindBy(className = "total_item") private WebElement itemCostTable;
  @FindBy(className = "total_tax") private WebElement taxTable;
  @FindBy(id = "checkout_total") private WebElement totalCost;
  /*** Shiping Info  **/
  @FindBy(id = "wpsc_checkout_form_9") private WebElement email;
  @FindBy(id = "wpsc_checkout_form_2") private WebElement fName;
  @FindBy(id = "wpsc_checkout_form_3") private WebElement lName;
  @FindBy(id = "wpsc_checkout_form_4") private WebElement address;
  @FindBy(id = "wpsc_checkout_form_5") private WebElement city;
  @FindBy(id = "wpsc_checkout_form_7") private WebElement billCountry;
  @FindBy(id = "wpsc_checkout_form_8") private WebElement pCode;
  @FindBy(id = "wpsc_checkout_form_18")private WebElement phone;
  @FindBy(id = "shippingSameBilling")  private WebElement sameShippingCB;

  /**
   *  Constructor
   * @param webDriver
   * @param wait
   */
  public CheckoutPage(WebDriver webDriver, FluentWait<WebDriver> wait){
    super(webDriver,wait);
    PageFactory.initElements(getWebDriver(),this);
  }

  public CheckoutPage(PageBase pb){
    super(pb);
  }


  public Boolean checkoutIphone() {
    //WebElement quantity = quantityContainer.findElement(By.name("quantity"));

    isWebElementVisible(quantityContainer);
    quantityText.clear();
    quantityText.sendKeys("1");
    updateBtn.click();
    isWebElementInvisible(By.id("checkout_page_container"));
    isWebElementVisible(continueBtn);
    continueBtn.click();

    return isWebElementVisible(infoContainer);
  }

  public void checkoutShipping(){

    Select countrySelect = new Select(countryDropdown);
    countrySelect.selectByVisibleText("USA");

    WebElement calculate = shippingContainer.findElement(By.name("wpsc_submit_zipcode"));
    calculate.click();

  }
  public boolean verifyTotalCost(int phonePrice){
    int shippingCost;
    int itemCost;
    int taxCost;
    int totalPrice;
    int total;

    WebElement shipping = shippingTable.findElement(By.className("pricedisplay "));
    String cost = shipping.getText();

    shippingCost = parseInt(cost);

    WebElement itemElement = itemCostTable.findElement(By.className("pricedisplay"));
    cost = itemElement.getText();
    itemCost = parseInt(cost);
    Assert.assertEquals(itemCost, phonePrice );

    cost = taxTable.findElement(By.className("pricedisplay")).getText();
    taxCost = parseInt(cost);

    cost = totalCost.getText();
    totalPrice = parseInt(cost);

    total = shippingCost + itemCost + taxCost;

    Assert.assertEquals(total, totalPrice );
    return true;
  }

  public int parseInt(String currency){
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    int icurrency = 0;

    try {
      icurrency = nf.parse(currency).intValue();
    }catch(ParseException ex){
      System.out.println("Cannot convert Currency to Int" + ex.getMessage());
    }
    return icurrency;
  }

  public boolean addCheckoutInfo(String email,String fname, String lname, String address, String city, String postal,
                                  String country, String phone){
    Select cSelect = new Select(billCountry);

    try {
      this.email.sendKeys(email);
      this.fName.sendKeys(fname);
      this.lName.sendKeys(lname);
      this.address.sendKeys(address);
      this.city.sendKeys(city);
      this.pCode.sendKeys(postal);
      cSelect.selectByVisibleText(country);
      this.phone.sendKeys(phone);
      this.sameShippingCB.click();
    }catch (NoSuchElementException ex ){
      System.out.println("cannot find element" + ex.getMessage());
    }
    purchaseBth.click();

    return isWebElementVisible(confirmOrder);
  }
}
