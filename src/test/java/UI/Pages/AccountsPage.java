package UI.Pages;

import UI.AbstractPageObject.HeadPageBase;
import UI.AbstractPageObject.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by vrajan on 8/1/2015.
 */
public class AccountsPage extends HeadPageBase {
  @FindBy(className = "user-profile-links") private WebElement accountClassName;
  @FindBy(xpath = "//*[@id=\"post-31\"]/div/div/div/a[2]") private WebElement detailsLink;
  @FindBy(className = "myaccount") private WebElement detailsTab;
  @FindBy(id = "wpsc_checkout_form_2") private WebElement fnameText;
  @FindBy(id = "wpsc_checkout_form_3") private WebElement lnameText;
  @FindBy(id = "wpsc_checkout_form_4") private WebElement addressText;
  @FindBy(name = "submit") private WebElement submitBtn;
  @FindBy(id = "sidebar-right") private WebElement rightBar;
  @FindBy(id = "loginform") private WebElement loginForm;
  /**
   * Constructor
   * @param webDriver
   * @param wait
   */
  public AccountsPage(WebDriver webDriver, FluentWait wait) {
    super(webDriver, wait);
    PageFactory.initElements(getWebDriver(), this);
  }

  public AccountsPage(PageBase pb) {
    super(pb);
    PageFactory.initElements(getWebDriver(),this);
  }

  public boolean editDetails( String address){
    isWebElementVisible(accountClassName);
    detailsLink.click();
    isWebElementVisible(detailsTab);
    isWebElementVisible(addressText);
    addressText.clear();
    addressText.sendKeys(address);
    submitBtn.click();
    isWebElementInvisible(By.name("submit"));
    return true;
  }

  public boolean verifyDetails(String verifyAddress){
    isWebElementVisible(accountClassName);
    detailsLink.click();
    isWebElementVisible(detailsTab);
    isWebElementVisible(addressText);
    Assert.assertTrue(addressText.getText().equals(verifyAddress),
                      "Address did not match Found:" + addressText.getText() +
                      " Expected: " + verifyAddress);
    return true;
  }

  public boolean logout(){
    WebElement logoutBtn = rightBar.findElement(By.linkText("Log out"));
    logoutBtn.click();
    isWebElementVisible(loginForm);
    return isWebElementVisible(loginForm);
  }

  public HomePage returnPO(){
    return new HomePage(this);
  }


}
