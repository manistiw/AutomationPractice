package pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class LoggedInHomePage {
    @Autowired
    BasePage basePage;



    private static final String url="?controller=my-account";
    private static final By fullNameText= By.className("account");


    @Step("Verify Home Page url")
    public boolean VerifyMyHomePage(){
        basePage.waitForUrl(url);
        return true;
    }

    @Step("Get username displayed on home page.")
    public String getUserName(){
        return basePage.getText(fullNameText);
    }
}
