package UI;

import UI.AbstractPageObject.HeadPageBase;
import UI.AbstractPageObject.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Created by vrajan on 7/29/2015.
 */
public class HomePage extends HeadPageBase {
    @FindBy(id = "main-nav") WebElement mainNav;
    @FindBy(id = "post-98") WebElement iphoneTab;

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
    this.selectScreen(productCategory,iphones);
    isWebElementVisible(iphoneTab);
    return true;
  }


}
