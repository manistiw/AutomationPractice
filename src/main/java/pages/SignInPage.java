package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class SignInPage {
    @Autowired
    BasePage basePage;

    private final Logger log = LoggerFactory.getLogger(SignInPage.class);

    public static final String createAccountBtnId="SubmitCreate";
    public static final By emailInputId=By.id("email_create");
    public static final By registeredEmailInputId=By.id("email");
    public static final By registeredPasswdId=By.id("passwd");
    public static final By SubmitBtn=By.id("SubmitLogin");

    @Step("enter email: {0} at SignIn Page")
    public void enterEmailId(String email){
        basePage.setText(emailInputId,email);
    }

    @Step("click create Account submit button")
    public void clickCreateAccount(){
        basePage.clickElement(By.id(createAccountBtnId));
    }

    @Step("enter login credentials and click login email: {0} and password: {1}")
    public void login(String email,String password){
        basePage.setText(registeredEmailInputId,email);
        basePage.setText(registeredPasswdId,password);
        basePage.clickElement(SubmitBtn);
    }


}
