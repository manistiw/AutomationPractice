package uicomponent;

import org.openqa.selenium.WebDriver;


public class WebDriverFactory {
    public static WebDriver getBrowserInstance(String browser,String url,int timeout){
        BrowserInstance browserInstance=new BrowserInstance();
        return browserInstance.setBrowserInstance(browser,url,timeout);
    }
}
