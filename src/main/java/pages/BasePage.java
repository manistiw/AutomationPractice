package pages;

import uicomponent.BrowserProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

@Component
public class BasePage {
    private final Logger log = LoggerFactory.getLogger(BasePage.class);

    @Step("Finding element By locator")
    protected WebElement findElement(By locator){
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return BrowserProvider.getDriver().findElement(locator);
    }

    @Step("clicking element By locator")
    protected void clickElement(By locator){
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        BrowserProvider.getDriver().findElement(locator).click();
    }
    @Step("Check if element Displayed on UI.")
    protected boolean isElementDisplayed(By element){
        return BrowserProvider.getDriver().findElement(element).isDisplayed();
    }

    @Step("Finding is WebElement present in html dom")
    protected boolean isElementPresent(By element){
        try {
            List<WebElement> elements = BrowserProvider.getDriver().findElements(element);
            if(elements != null && elements.size() > 0) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }

    @Step("getting text of the Element")
    protected String getText(By locator){
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement elem=BrowserProvider.getDriver().findElement(locator);
        if(elem.getText().isEmpty())
            return elem.getAttribute("value");
        return elem.getText().trim();
    }

    @Step("selecting dropdown value by Value: {1}")
    protected void selectDropDownValue(By locater,String value){
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(locater));
        Select sel=new Select(BrowserProvider.getDriver().findElement(locater));
        sel.selectByValue(value);
    }

    @Step("selecting dropdown value with visibleText: {1}")
    protected void selectDropDownVisibleText(By locater,String visibleText){
        log.info("selecting value from dropdown: "+visibleText);
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(locater));
        Select sel=new Select(BrowserProvider.getDriver().findElement(locater));
        sel.selectByVisibleText(visibleText);
    }

    @Step("typing text: {1} in input field after clearing the input field")
    protected void setText(By locator,String text){
        log.info("setting value in input field: "+text);
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
    }

    @Step("Clicking on element using mouse actions")
    protected void clickUsingAction(By locator){
        log.info("clicking on element using action");
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions action=new Actions(BrowserProvider.getDriver());
        WebElement element=BrowserProvider.getDriver().findElement(locator);
        action.moveToElement(element);
        ((JavascriptExecutor) BrowserProvider.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        action.click().build().perform();
    }
    @Step("switching to Iframe with id: {0}")
    protected void switchToFrame(String id){
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(), 15);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));

    }
    @Step("waiting for url to be equal to given url: {0}.")
    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(),15);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 ->BrowserProvider.getDriver().getCurrentUrl().equals(url);
        wait.until(urlIsCorrect);
    }
    @Step("waiting for url to ends with text: {0}.")
    public void waitForUrlContains(String text) {
        WebDriverWait wait = new WebDriverWait(BrowserProvider.getDriver(),15);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 ->BrowserProvider.getDriver().getCurrentUrl().contains(text);
        wait.until(urlIsCorrect);
    }

    @Step("get Curret Url")
    public String getCurrentUrl() {
        return BrowserProvider.getDriver().getCurrentUrl();
    }

    public String getCssProperty(WebElement element,String attribute){
        return element.getCssValue(attribute);
    }


}
