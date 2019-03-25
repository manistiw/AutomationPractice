package Listners;

import uicomponent.BrowserProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

public class TestListner implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment(value = "page Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = BrowserProvider.getDriver();
        if (driver instanceof WebDriver) {
            saveScreenshotPNG(driver);
        }

    }
}