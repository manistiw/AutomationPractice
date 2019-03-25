package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class ProductCheckout {
    @Autowired
    BasePage basePage;

    private static final String productNameId=".//h5/a[@title='%s']";
    private static final By addToCart=By.xpath(".//p[@id='add_to_cart']/button[@type='submit']");
    private static final By proceedToCheckOut1=By.xpath("(.//a[@title='Proceed to checkout'])[1]");
    private static final By proceedToCheckOut2=By.xpath("(.//a[@title='Proceed to checkout']/span)[2]");
    private static final By proceedToCheckOut3=By.xpath("(.//button[@name='processAddress']/span)[1]");
    private static final By proceedToCheckOut4=By.xpath("(.//button[@name='processCarrier']/span)[1]");
    private static final By acceptTermsAndCondtn=By.id("uniform-cgv");
    private static final By payBybankWire=By.xpath("(.//a[@title='Pay by bank wire']/span)[1]");
    private static final By confirmOrder=By.xpath("(.//button[@type='submit']/span)[2]");
    private static final String orderConfirmation="?controller=order-confirmation";
    private static final By lastStep=By.id("step_end");

    private static final String orderConfirmationText="Your order on My Store is complete.";
    private static final By orderConfirmationId=By.xpath(".//p[@class='cheque-indent']/strong");

    private static final String finalStepBorderColor="#399b49";



    @Step("composite steps of selecting product and completing checkout")
    public void productCheckOut(String productName){
        basePage.clickUsingAction(By.xpath(String.format(productNameId,productName)));
        basePage.clickUsingAction(addToCart);
        basePage.clickUsingAction(proceedToCheckOut1);
        basePage.clickUsingAction(proceedToCheckOut2);
        basePage.clickUsingAction(proceedToCheckOut3);
        basePage.clickUsingAction(acceptTermsAndCondtn);
        basePage.clickUsingAction(proceedToCheckOut4);
        basePage.clickElement(payBybankWire);
        basePage.clickElement(confirmOrder);


    }

    @Step("Verify final step is shown in green while doing checkout.")
    public boolean verifyFinalStepGreenColor(){
        String property= Color.fromString(basePage.getCssProperty(basePage.findElement(lastStep),"border-top-color")).asHex();
        return property.equalsIgnoreCase(finalStepBorderColor);

    }

    @Step("Verify order confirmation text appeared on screen")
    public boolean verifyOrderConfirmationText(){
        String confirmationText=basePage.findElement(orderConfirmationId).getText();
        return confirmationText.equalsIgnoreCase(orderConfirmationText);
    }

    @Step("verify final step url is shown correctly")
    public  boolean verifyFinalStepUrl(){
        String url= basePage.getCurrentUrl();
        return url.contains(orderConfirmation);
    }
}
