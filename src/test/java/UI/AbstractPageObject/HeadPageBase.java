package UI.AbstractPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Created by vrajan on 7/29/2015.
 */
public abstract class HeadPageBase extends PageBase {

  @FindBy(id = "menu-item-33") protected WebElement productCategory;
  @FindBy(id = "menu-item-37") protected WebElement iphones;


  public HeadPageBase(WebDriver webDriver, FluentWait wait){
    super(webDriver,wait);
  }

  public HeadPageBase(PageBase pb){
    super(pb);
  }

  private void pointAt(WebElement element) {
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(element);
    actions.build().perform();
  }

  protected void selectScreen(WebElement headElement, WebElement element) {
    //Try to display the head element's list item up to 3 times.
    int i = 0;
    do {
      webDriver.switchTo().window(webDriver.getWindowHandle());
      pointAt(headElement);
      pointAt(element);
      i++;
    } while(!isWebElementVisible(element) && i <= 5);

    element.click();
  }
}