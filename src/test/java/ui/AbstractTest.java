package ui;

import uicomponent.BrowserProvider;
import config.ApplicationConfigContext;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;

@ContextConfiguration(classes = {ApplicationConfigContext.class})
public class AbstractTest extends AbstractTestNGSpringContextTests {

    @AfterSuite(alwaysRun = true)
    public void doAfterSuite(){
        System.out.println("After Suite");
        WebDriver driver = BrowserProvider.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }




}
