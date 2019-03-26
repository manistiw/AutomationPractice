package uicomponent;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BrowserInstance {
    private final Logger log = LoggerFactory.getLogger(BrowserInstance.class);

    public WebDriver setBrowserInstance(String browser,String baseUrl,int implicitlytimeoutinmillisec){
        WebDriver driver=null;
        switch(browser.toLowerCase())
        {
            case "chrome_headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                driver.manage().timeouts().implicitlyWait(implicitlytimeoutinmillisec, TimeUnit.SECONDS);
                driver.get(baseUrl);
                break;

            case  "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.get(baseUrl);
                break;

            case  "ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings();
                ieOptions.withInitialBrowserUrl(baseUrl);
                ieOptions.enablePersistentHovering();
                ieOptions.destructivelyEnsureCleanSession();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                WebDriverManager.iedriver().setup();
                driver=new InternetExplorerDriver(ieOptions);
                driver.get(baseUrl);
                driver.manage().window().maximize();
                break;

            case  "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(implicitlytimeoutinmillisec*10, TimeUnit.SECONDS);
                driver.get(baseUrl);
                break;

            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver(options);
                driver.get(baseUrl);
                log.info("Please Provide a Valid Browser, Running in Chrome by default");
        }
        return driver;
    }
}
