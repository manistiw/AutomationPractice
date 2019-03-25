package Listners;

import uicomponent.BrowserProvider;
import uicomponent.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class WebDriverListner implements IInvokedMethodListener {

    @Override
    @Parameters({"url","browser","timeout"})
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
            String url = method.getTestMethod().getXmlTest().getLocalParameters().get("url");
            int timeout = Integer.parseInt(method.getTestMethod().getXmlTest().getLocalParameters().get("timeout"));
            WebDriver driver = WebDriverFactory.getBrowserInstance(browserName,url,timeout);
            BrowserProvider.setWebDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = BrowserProvider.getDriver();
            if (driver != null) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestListner.saveScreenshotPNG(driver);
                driver.quit();
            }
        }
    }
}
