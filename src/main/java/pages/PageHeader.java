package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class PageHeader {

    private static final String signInBtn="login";
    private static final By logOutBtn= By.className("logout");
    private static final String menuCategory=".//a[@title='%s']";

    @Autowired
    BasePage basePage;

    @Step("click SignIn Button in PageHeader.")
    public void clickSignIn(){
        basePage.clickElement(By.className(signInBtn));
    }

    @Step("click Logout Button in PageHeader.")
    public boolean clickLogout(){
        if(basePage.isElementDisplayed(logOutBtn)) {
            basePage.clickElement(logOutBtn);
            return true;
        }
        else{
            return false;
        }
    }
    @Step("Select Menu in PageHeader with text: {0}")
    public void selectMenu(String menuText){
        basePage.clickUsingAction(By.xpath(String.format(menuCategory,menuText)));
    }

    @Step("LogOut user if logged in already.")
    public void logOutIfUserLoggedIn(){
        if(basePage.isElementPresent(logOutBtn)){
            clickLogout();
        }
    }


}
